package org.core.port.dto;

public record DeleteTodoRequest(String todoListId, int todoId) {
}
