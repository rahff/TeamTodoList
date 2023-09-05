package org.core.port.dto;

public record AddTodoRequest(String todoListId, TodoDto todoDto) {
}
