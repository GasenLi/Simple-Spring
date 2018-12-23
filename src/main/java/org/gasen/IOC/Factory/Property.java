package org.gasen.IOC.Factory;

public class Property {
    private String name;
    private Bean reference;
    private String value;

    public Property(String name, Bean reference){
        this.name = name;
        this.reference = reference;
    }

    public Property(String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bean getReference() {
        return reference;
    }

    public void setReference(Bean reference) {
        this.reference = reference;
    }
}
