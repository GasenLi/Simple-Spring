package org.gasen.IOC.Factory;

public class Property {
    private String name;
    private Bean reference;

    public Property(String name, Bean reference){
        this.name = name;
        this.reference = reference;
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
