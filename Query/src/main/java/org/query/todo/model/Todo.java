package org.query.todo.model;

public record Todo(String id, String description, boolean done, String deadline, String createdAt) {
}
