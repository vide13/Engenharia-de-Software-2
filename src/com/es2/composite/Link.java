package com.es2.composite;

public class Link extends Menu {

    String url;

    public Link(){
    }
    public String getURL(){
        return url;
    }

    public void setURL(String URL) {
        url = URL;
    }

    @Override
    public void showOptions() {
        System.out.println(label);
        System.out.println(url);
    }
}
