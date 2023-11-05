package org.example.controllers.api.todo.jsonPayloads.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record AddTodoRequestBody(String todoId, String listId, String description, @JsonFormat(pattern = "yyyy-MM-dd") LocalDate deadline, @JsonFormat(pattern = "yyyy-MM-dd") LocalDate createdAt) {
}
