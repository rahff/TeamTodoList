package org.example.controllers.api.todo.jsonPayloads.request;

public record DeleteTodoRequestBody(String todoListId, String todoId) {
}
