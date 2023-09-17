package org.todo.port.dto;

public enum UserPlan {
  FREE {
    public String toString() {
      return "FREE";
    }
  }, PREMIUM {
    public String toString() {
      return "PREMIUM";
    }
  };

  public abstract String toString();

}
