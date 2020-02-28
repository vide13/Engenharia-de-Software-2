package com.es2.objectpool;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ReusablePool {
    private static ReusablePool instance = null;
    private int maxPoolSize;
    private ArrayList<HttpURLConnection> usedConnection, freeConnection;

    private ReusablePool() {
        usedConnection = new ArrayList<>();
        freeConnection = new ArrayList<>();
        maxPoolSize = 10;
    }

    public static ReusablePool getInstance() {
        if (instance == null) {
            synchronized (ReusablePool.class) {
                if (instance == null) {
                    instance = new ReusablePool();
                }
            }
        }
        return instance;
    }

    public synchronized HttpURLConnection acquire() throws IOException, PoolExhaustedException {
        if (usedConnection.size() >= maxPoolSize) {
            throw new PoolExhaustedException();
        }
        if (freeConnection.size() > 0) {
            for (HttpURLConnection e : freeConnection) {
                freeConnection.remove(e);
                usedConnection.add(e);
                e.connect();
                return (e);
            }
        }
        HttpURLConnection urlConnection = (HttpURLConnection) new URL("http://ipv.pt").openConnection();
        usedConnection.add(urlConnection);
        return urlConnection;
    }

    public synchronized void release(HttpURLConnection conn) throws ObjectNotFoundException {
        if (usedConnection.contains(conn)) {
            conn.disconnect();
            usedConnection.remove(conn);
            freeConnection.add(conn);
        } else {
            throw new ObjectNotFoundException();
        }
    }

    public synchronized void resetPool() {
        for (HttpURLConnection e : freeConnection) {
            e.disconnect();
        }
        freeConnection.clear();
        for (HttpURLConnection e : usedConnection) {
            e.disconnect();
        }
        usedConnection.clear();
    }

    public synchronized void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
}
