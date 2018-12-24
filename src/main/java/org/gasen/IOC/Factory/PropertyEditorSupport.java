package org.gasen.IOC.Factory;

public class PropertyEditorSupport {
    private Object value;

    public Object getValue() {
        return value;
    }

    public void setAsText(String text) throws java.lang.IllegalArgumentException {
        if (value instanceof String) {
            setValue(text);
            return;
        }
        throw new java.lang.IllegalArgumentException(text);
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
