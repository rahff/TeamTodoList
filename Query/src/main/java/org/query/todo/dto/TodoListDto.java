package org.query.todo.dto;

import org.query.todo.model.Todo;

import java.util.List;

public record TodoListDto(String id, String name, String createdAt, List<Todo> todos) {
}
