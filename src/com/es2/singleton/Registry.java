package com.es2.singleton;

/**
 * The type Registry.
 */
public class Registry {

    private String path = null;
    private String connectionString = null;
    private static volatile Registry instance = null;

    private Registry() {}
    
    public static Registry Registry(String path, String connectionString) {
        if (instance == null) {
            synchronized (Registry.class) {
                if (instance == null) {
                    instance = new Registry();
                }
            }
        }
        return instance;
    }

    public String getPath() {
        return path;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public static Registry getInstance() {
        if(instance == null) {
            synchronized (Registry.class) {
                if(instance == null) {
                    instance = new Registry();
                }
            }
        }
        return instance;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public static void setInstance(Registry instance) {
        Registry.instance = instance;
    }
}
