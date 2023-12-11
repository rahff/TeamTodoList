package org.example.transactions.team;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.team.application.commands.FireTeammateCommand;
import org.team.ports.dto.FireTeammateRequest;
import org.todo.application.commands.DeleteUserTodoLists;
import org.todo.port.dto.DeleteUserTodoListsRequest;




public class FireTeammateTransaction {

    private final FireTeammateCommand fireTeammateCommand;
    private final DeleteUserTodoLists deleteUserTodoListsCommand;

    public FireTeammateTransaction(FireTeammateCommand fireTeammateCommand, DeleteUserTodoLists deleteUserTodoListsCommand) {
        this.fireTeammateCommand = fireTeammateCommand;
        this.deleteUserTodoListsCommand = deleteUserTodoListsCommand;
    }

    @Transactional
    public void execute(FireTeammateRequest request){
       fireTeammateCommand.execute(request);
       deleteUserTodoListsCommand.execute(new DeleteUserTodoListsRequest(request.userId()));
    }
}
