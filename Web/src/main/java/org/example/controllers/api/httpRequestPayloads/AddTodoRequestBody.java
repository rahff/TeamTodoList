package org.example.controllers.api.httpRequestPayloads;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record AddTodoRequestBody(String todoId, String listId, String description, @JsonFormat(pattern = "yyyy-MM-dd") LocalDate deadline) {
}
