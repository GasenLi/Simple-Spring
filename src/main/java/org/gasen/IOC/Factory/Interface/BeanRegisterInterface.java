package org.gasen.IOC.Factory.Interface;

import org.gasen.IOC.Factory.Bean;

import java.util.List;

public interface BeanRegisterInterface {

    /*向工厂内注册BeanDefinition*/
    void registerBean(List<Bean> beans);

    /*向工厂内注册Bean实例对象*/
    void registerInstanceMapping(String ID, Object instance);

}
