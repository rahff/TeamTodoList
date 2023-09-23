package org.example.controllers.api.todo.jsonPayloads;

public record DoneTodoRequestBody(String todoListId, String todoId) {
}
