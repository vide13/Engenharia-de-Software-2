package com.es2.factorymethod;

public class Software extends Object implements Product {

    String brand = null;

    protected Software() {
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }
}
