package org.core.port.dto;

public record CreateTodoListRequest(UserContext userCtx, String id, String todoListName) {
}
