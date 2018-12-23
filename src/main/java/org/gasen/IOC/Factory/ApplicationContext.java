package org.gasen.IOC.Factory;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.gasen.IOC.Factory.Interface.ApplicationContextInterface;
import org.gasen.IOC.Factory.Interface.BeanRegisterInterface;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext implements ApplicationContextInterface, BeanRegisterInterface {
    public Map<String, Object> instanceMapping = new ConcurrentHashMap<>();

    //保存所有的Bean
    public List<Bean> beans = new ArrayList<>();

    //配置文件
    //private Properties config = new Properties();

    public ApplicationContext(String location) {

            register(location);

            //初始化Bean
            createBean();

            //依赖注入
            implant();

            //执行默认方法
            defaultMethod();

    }

    private void defaultMethod(){
        for(Bean bean: beans){
            if(bean.getInit_method()!=null){
                runInitMethod(bean.getInit_method(), instanceMapping.get(bean.getID()));
            }
        }
    }

    private void register(String location){
        BeanParser beanParser = new BeanParser(this);
        beanParser.parse(location);
    }

    private void createBean(){
        BeanCreator creator = new BeanCreator(this);
        creator.create(beans);
    }

    private void implant(){
        Implant implant = new Implant(this);
        implant.implant();
    }

    //运行默认方法
    private void runInitMethod(String init_method, Object instance){
        try {
            Method initMethod = instance.getClass().getMethod(init_method, new Class[]{});
            initMethod.invoke(instance, new Object[]{});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String ID) {
        return instanceMapping.get(ID);
    }

    @Override
    public <T> T getBean(String ID, Class<T> clazz) {
        return (T)instanceMapping.get(ID);
    }

    @Override
    public Map<String, Object> getBeans() {
        return instanceMapping;
    }

    @Override
    public void registerBean(List<Bean> beans) {
        this.beans.addAll(beans);
    }

    @Override
    public void registerInstanceMapping(String ID, Object instance) {
        instanceMapping.put(ID, instance);
    }
}
