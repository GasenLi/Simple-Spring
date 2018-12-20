package org.gasen.IOC.Factory;

public class Bean {
    private String ID;
    private Class aClass;

    public Bean(String ID, Class aClass){
        this.ID = ID;
        this.aClass = aClass;
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
