package org.todo.port.dto;

public record AddTodoRequest(String todoListId, TodoDto todoDto) {
}
