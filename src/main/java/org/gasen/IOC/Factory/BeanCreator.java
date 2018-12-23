package org.gasen.IOC.Factory;

import org.gasen.IOC.Factory.Interface.BeanRegisterInterface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class BeanCreator {

    private BeanRegisterInterface register;

    public BeanCreator(BeanRegisterInterface register){
        this.register = register;
    }

    public void create(List<Bean> beans){
        for(Bean bean : beans){
            doCreate(bean);
        }
    }

    private void doCreate(Bean bean){
        Object instance = bean.getInstance();

        this.register.registerInstanceMapping(bean.getID(), instance);
    }


}
