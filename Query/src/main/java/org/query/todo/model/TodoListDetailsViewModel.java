package org.query.todo.model;


import org.query.todo.model.TodoList;

import java.util.List;

public record TodoListDetailsViewModel(TodoList info, List<Todo> todos) {
}
