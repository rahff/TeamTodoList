package org.example.transactions.todo;


import jakarta.transaction.Transactional;
import org.shared.api.Command;
import org.springframework.stereotype.Service;
import org.todo.port.dto.DeleteUserTodoListsRequest;

@Service
public class DeleteUserTodoListsTransaction {

    private final Command<DeleteUserTodoListsRequest> deleteUserTodoListsCommand;

    public DeleteUserTodoListsTransaction(Command<DeleteUserTodoListsRequest> deleteUserTodoListsCommand) {
        this.deleteUserTodoListsCommand = deleteUserTodoListsCommand;
    }

    @Transactional
    public void execute(DeleteUserTodoListsRequest request){
        deleteUserTodoListsCommand.execute(request);
    }
}
