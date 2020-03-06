package com.es2.objectpool;

import java.io.File;

/**
 * The type com.es2.com.es2.objectpool.End.
 */
public class End {
    /**
     * Instantiates a new com.es2.com.es2.objectpool.End.
     */
    public End() {
        File f = new File("runningTest.tmp");
        f.delete();
    }
}
