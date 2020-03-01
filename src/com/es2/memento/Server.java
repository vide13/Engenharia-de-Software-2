package com.es2.memento;

import java.util.ArrayList;

public class Server {

    private ArrayList<String> studentNames = new ArrayList<>();

    public void addStudent(String studentName) throws ExistingStudentException {
        if (studentNames.contains(studentName)) {
            throw new ExistingStudentException();
        }
        studentNames.add(studentName);
    }

    public Memento backup() {
        ArrayList studentNamesClone = (ArrayList) studentNames.clone();
        return new Memento(studentNamesClone);
    }

    public void restore(Memento state) {
        studentNames = state.getState();
    }

    public ArrayList<String> getStudentNames() {
        return studentNames;
    }
}
