package org.gasen.IOC.Factory;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.gasen.IOC.Factory.Interface.ApplicationContextInterface;
import org.gasen.IOC.Factory.Interface.BeanRegisterInterface;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext implements ApplicationContextInterface, BeanRegisterInterface {
    private Map<String, Object> instanceMapping = new ConcurrentHashMap<>();

    //保存所有的Bean
    private List<Bean> beans = new ArrayList<>();

    //配置文件
    private Properties config = new Properties();

    public ApplicationContext(String location){
        InputStream inputStream = null;

        try{
            inputStream = this.getClass().getResourceAsStream(location);

            config.load(inputStream);

            register();

            createBean();

            implant();


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void register(){
        BeanParser beanParser = new BeanParser(this);
        beanParser.parse(config);
    }

    private void createBean(){
        BeanCreator creator = new BeanCreator(this);
        creator.create(beans);
    }

    private void implant(){
        Implant implant = new Implant();
        implant.implant(instanceMapping);
    }

    @Override
    public Object getBean(String ID) {
        return null;
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
