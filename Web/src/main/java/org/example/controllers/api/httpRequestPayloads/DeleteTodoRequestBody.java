package org.example.controllers.api.httpRequestPayloads;

public record DeleteTodoRequestBody(String todoListId, String todoId) {
}
