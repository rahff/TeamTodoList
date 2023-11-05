package org.example.controllers.api.todo.jsonPayloads.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CreateTodoRequestBody(String id, String todoListName, String ref, @JsonFormat(pattern = "yyyy-MM-dd") LocalDate createdAt) {
}
