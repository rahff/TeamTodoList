package org.query.todo.api;

import org.query.todo.model.ListOfTodoListViewModel;
import org.query.todo.model.TodoList;
import org.query.todo.spi.TodoListDataAccess;

public class ListOfTodoListViewQuery {

    private final TodoListDataAccess todoListDataAccess;

    public ListOfTodoListViewQuery(TodoListDataAccess todoListDataAccess) {
        this.todoListDataAccess = todoListDataAccess;
    }

    public ListOfTodoListViewModel query(String userId) {
        var userTodoListDtos = todoListDataAccess.getUserTodoList(userId);
        var userTodoLists = userTodoListDtos.stream().map(dto -> new TodoList(dto.id(), dto.name(), dto.createdAt())).toList();
        return new ListOfTodoListViewModel(userTodoLists);
    }
}
