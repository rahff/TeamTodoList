package org.example.controllers.api.todo.jsonPayloads;

public record DeleteTodoRequestBody(String todoListId, String todoId) {
}
