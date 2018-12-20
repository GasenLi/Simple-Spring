package org.gasen.IOC.Factory.Interface;

import java.util.Map;

public interface ApplicationContextInterface {

    /*根据ID获取Bean*/
    Object getBean(String ID);

    /*根据ID获取特定类型的Bean*/
    <T> T getBean(String ID, Class<T> clazz);

    /*获取工厂内的所有的Bean*/
    Map<String, Object> getBeans();
}
