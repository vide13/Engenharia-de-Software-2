package com.es2.factorymethod;

public class Computer extends Object implements Product {

    String brand = null;

    protected Computer() {}

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }
}
