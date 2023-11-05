package org.query.todo.api;



import org.query.todo.model.TodoList;
import org.query.todo.spi.TodoListDataAccess;
import org.query.todo.model.TodoListDetailsViewModel;

import java.util.List;


public class TodoListDetailsViewQuery {

    private final TodoListDataAccess todoListDataAccess;

    public TodoListDetailsViewQuery(TodoListDataAccess todoListDataAccess) {
        this.todoListDataAccess = todoListDataAccess;
    }

    public TodoListDetailsViewModel query(String todoListId) {
        var todoListDto = todoListDataAccess.getTodoListById(todoListId).orElseThrow();
        return new TodoListDetailsViewModel(new TodoList(todoListDto.id(), todoListDto.name(), todoListDto.createdAt()), todoListDto.todos());
    }
}
