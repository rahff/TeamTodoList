package org.example.controllers.api.todo.jsonPayloads.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.todo.port.dto.TodoDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record TodoJson(String todoId, String listId, String description, String deadline, String createdAt) {
    public static TodoJson from(TodoDto dto, String listId){
        return new TodoJson(dto.id(),
                listId,
                dto.description(),
                dto.deadLine().format(DateTimeFormatter.ISO_DATE),
                dto.createdAt().format(DateTimeFormatter.ISO_DATE));
    }
}
