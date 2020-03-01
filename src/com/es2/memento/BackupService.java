package com.es2.memento;

import java.util.ArrayList;

public class BackupService {

    private Server server;
    private ArrayList<Memento> mementos = new ArrayList<>();


    public BackupService(Server server) {
        this.server = server;
    }


    public void takeSnapshot() {
        mementos.add(server.backup());
    }

    public void restoreSnapshot(int snapshotNumber) throws NotExistingSnapshotException {
        if (snapshotNumber < 0 || snapshotNumber >= mementos.size())
            throw new NotExistingSnapshotException();
        server.restore(mementos.get(snapshotNumber));
    }
}
