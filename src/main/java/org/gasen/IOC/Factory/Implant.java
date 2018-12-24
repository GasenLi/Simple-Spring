package org.gasen.IOC.Factory;

import org.gasen.IOC.Factory.Interface.BeanRegisterInterface;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    //注入带Bean的属性
    public void beanImplant(Bean bean, Property property){
        try {
            Field implantedField = instanceMapping.get(bean.getID()).getClass().getDeclaredField(property.getName());
            Object implantObject = instanceMapping.get(property.getReference().getID());

            implantedField.setAccessible(true);
            implantedField.set(instanceMapping.get(bean.getID()), implantObject);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //注入带值的属性
    public void beanImplantWithNoBean(Bean bean, Property property){
        try {
            Field implantedField = instanceMapping.get(bean.getID()).getClass().getDeclaredField(property.getName());

            Object value = null;
            if(property.getPropertyEditor() == null){
                value = property.getValue();
            }else {
                Class propertyEditorClass = Class.forName(property.getPropertyEditor());
                Object propertyEditor = propertyEditorClass.newInstance();

                Method setAsText =propertyEditorClass.getMethod("setAsText", new Class[]{String.class});
                setAsText.invoke(propertyEditor, new Object[]{property.getValue()});

                PropertyEditorSupport propertyEditorSupport = (PropertyEditorSupport) propertyEditor;
                value = propertyEditorSupport.getValue();
            }


            implantedField.setAccessible(true);
            implantedField.set(instanceMapping.get(bean.getID()), value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public void implant(){
        for(Bean bean : beans){
            for(Property property : bean.getProperties()){
                if(property.getReference() != null){
                    beanImplant(bean, property);
                }else {
                    beanImplantWithNoBean(bean, property);
                }
            }
        }
    }
}
