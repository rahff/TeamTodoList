package org.todo.port.dto;

public enum UseRole {
  TEAMMATE {
    public String toString() {
      return "TEAMMATE";
    }
  }, MANAGER {
    public String toString() {
      return "MANAGER";
    }
  };

  public abstract String toString();

}
