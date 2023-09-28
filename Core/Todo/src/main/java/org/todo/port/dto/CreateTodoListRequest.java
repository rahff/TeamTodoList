package org.todo.port.dto;

public record CreateTodoListRequest(UserContext userCtx, String todoListId, String todoListName, String ref) {
}
