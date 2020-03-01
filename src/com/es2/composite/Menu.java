package com.es2.composite;

public abstract class Menu {

    public String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public abstract void showOptions();
}
