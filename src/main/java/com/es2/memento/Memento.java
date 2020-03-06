package com.es2.memento;

import java.util.ArrayList;

public class Memento {

  private ArrayList<String> studentNames;

  public Memento(ArrayList<String> studentNames) {
    this.studentNames = studentNames;
  }

  protected ArrayList<String> getState() {
    return studentNames;
  }
}
