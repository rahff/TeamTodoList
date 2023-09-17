package org.todo.port.dto;

public record DeleteTodoRequest(String todoListId, String todoId) {
}
