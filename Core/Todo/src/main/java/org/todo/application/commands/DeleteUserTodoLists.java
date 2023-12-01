package org.todo.application.commands;

import org.shared.api.Command;
import org.todo.port.dto.DeleteUserTodoListsRequest;
import org.todo.port.spi.TodoListRepository;

public class DeleteUserTodoLists implements Command<DeleteUserTodoListsRequest> {

    private final TodoListRepository todoListRepository;

    public DeleteUserTodoLists(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public void execute(DeleteUserTodoListsRequest request) {
        var userTodoLists = todoListRepository.getTodoListsByUserRef(request.userId());
        userTodoLists.parallelStream().forEach((dto)-> todoListRepository.delete(dto.listId()));
    }
}
