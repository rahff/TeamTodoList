package org.todo.port.dto;

public record CreateTodoListRequest(UserContext userCtx, String id, String todoListName) {
}
