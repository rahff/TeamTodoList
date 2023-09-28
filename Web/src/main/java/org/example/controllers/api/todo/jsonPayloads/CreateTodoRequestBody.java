package org.example.controllers.api.todo.jsonPayloads;

public record CreateTodoRequestBody(String id, String todoListName, String ref) {
}
