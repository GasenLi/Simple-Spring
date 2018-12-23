package org.gasen.IOC.Factory;

import java.util.ArrayList;

public class Bean {
    private String ID;
    private Class aClass;
    private String init_method;
    private ArrayList<Property> properties = new ArrayList<>();

    public Bean(String ID, Class aClass){
        this.ID = ID;
        this.aClass = aClass;
    }

    public Bean(String ID, Class aClass, String init_method){
        this.ID = ID;
        this.aClass = aClass;
        this.init_method = init_method;
    }

    public String getInit_method() {
        return init_method;
    }

    public void setInit_method(String init_method) {
        this.init_method = init_method;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property) {
        this.properties.add(property);
    }

    public String getID() {
        return ID;
    }

    public void setID(String id) {
        this.ID = ID;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Object getInstance(){
        try{
            return aClass.newInstance();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
