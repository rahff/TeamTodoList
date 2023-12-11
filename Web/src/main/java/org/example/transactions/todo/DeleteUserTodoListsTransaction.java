package org.example.transactions.todo;


import jakarta.transaction.Transactional;
import org.todo.application.commands.DeleteUserTodoLists;
import org.todo.port.dto.DeleteUserTodoListsRequest;


public class DeleteUserTodoListsTransaction {

    private final DeleteUserTodoLists deleteUserTodoListsCommand;

    public DeleteUserTodoListsTransaction(DeleteUserTodoLists deleteUserTodoListsCommand) {
        this.deleteUserTodoListsCommand = deleteUserTodoListsCommand;
    }

    @Transactional
    public void execute(DeleteUserTodoListsRequest request){
        deleteUserTodoListsCommand.execute(request);
    }
}
