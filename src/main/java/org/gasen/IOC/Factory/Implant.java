package org.gasen.IOC.Factory;

import org.gasen.IOC.Factory.Interface.BeanRegisterInterface;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class Implant {
    private BeanRegisterInterface application;
    private Map<String, Object> instanceMapping;
    private List<Bean> beans;

    public Implant(ApplicationContext application){
        this.application = application;
        instanceMapping = application.instanceMapping;
        beans = application.beans;
    }

    public void beanImplant(Bean bean){
        try {
            Field implantedField = instanceMapping.get(bean.getID()).getClass().getDeclaredField(bean.getProperty().getName());
            Object implantObject = instanceMapping.get(bean.getProperty().getReference().getID());

            implantedField.setAccessible(true);
            implantedField.set(instanceMapping.get(bean.getID()), implantObject);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public void implant(){
        for(Bean bean : beans){
            if(bean.getProperty()!=null){
                beanImplant(bean);
            }
        }
    }
}
