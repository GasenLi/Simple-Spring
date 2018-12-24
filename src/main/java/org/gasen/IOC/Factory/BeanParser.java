package org.gasen.IOC.Factory;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.gasen.IOC.Factory.Interface.BeanRegisterInterface;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BeanParser {

    //容器注册对象
    private BeanRegisterInterface register;

    private List<Bean> beans = new ArrayList<>();


    public BeanParser(BeanRegisterInterface register) {
        this.register = register;
    }

    public void parse(String location) {
        Document document = scan(location);
        readXML(document);
        this.register.registerBean(beans);
    }

    private Document scan(String location) {
        SAXReader saxReader = new SAXReader();
        Document document = null;
        File configFile = null;

        try {
            configFile = new File(this.getClass().getClassLoader().getResource("").toURI().getPath() + location);
            document = saxReader.read(configFile);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return document;
    }

    //读取配置文件
    private void readXML(Document document) {
        Element root = document.getRootElement();

        //遍历bean
        Iterator<Element> elementIterator = root.elementIterator();
        while (elementIterator.hasNext()) {
            Element beanNode = elementIterator.next();

            List<Attribute> beanAttrList = beanNode.attributes();
            String ID = null, aclass = null, init_method = null;
            for (Attribute beanAttr : beanAttrList) {
                String attrName = beanAttr.getName();

                switch (attrName){
                    case "id" : ID = beanAttr.getValue();break;
                    case "class" : aclass = beanAttr.getValue();break;
                    case "init-method" : init_method = beanAttr.getValue();break;
                }
            }

            Bean bean = null;
            if(ID!=null && aclass!=null){
                bean = doRegisterBean(ID, aclass, init_method);
            }



            //遍历Property
            if(bean != null){
                Iterator<Element> beanAttrIterator = beanNode.elementIterator();

                while (beanAttrIterator.hasNext()) {
                    Element beanProperNode = beanAttrIterator.next();

                    List<Attribute> beanProperList = beanProperNode.attributes();
                    String name = null, reference = null, value = null, propertyEditor = null;
                    for (Attribute beanProper : beanProperList) {
                        String attrName = beanProper.getName();

                        switch (attrName){
                            case "name" : name = beanProper.getValue();break;
                            case "ref" : reference = beanProper.getValue();break;
                            case "value" : value = beanProper.getValue();break;
                            case "propertyEditor" : propertyEditor = beanProper.getValue();break;
                        }
                    }

                    if(name!=null && reference!=null){
                        doRegisterProperty(bean, name, reference);
                    }else if(name!=null && value!=null){
                        doRegisterPropertyWithValue(bean, name, value, propertyEditor);
                    }
                }
            }
        }

    }

    //存储Bean
    private Bean doRegisterBean(String ID, String aclass, String init_method){
        try {
            Class c = Class.forName(aclass);

            Bean bean;
            if(init_method == null){
                bean = new Bean(ID, c);
            }else {
                bean = new Bean(ID, c, init_method);
            }

            beans.add(bean);

            return bean;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    

    //存储与bean对应的Property
    private void doRegisterProperty(Bean implantedBean, String name, String reference){
        Property property;

        for(Bean implantBean : this.beans){
            if(reference.equals(implantBean.getID())){
                property = new Property(name, implantBean);
                implantedBean.addProperty(property);

                break;
            }
        }
    }

    //存储带值的property
    private void doRegisterPropertyWithValue(Bean implantedBean, String name, String value, String propertyEditor){
        Property property;

        property = new Property(name, value, propertyEditor);
        implantedBean.addProperty(property);
    }

}
