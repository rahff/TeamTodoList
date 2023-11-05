package org.todo.port.dto;

import java.time.LocalDate;

public record CreateTodoListRequest(UserContext userCtx, String todoListId, String todoListName, String ref, LocalDate createdAt) {
}
