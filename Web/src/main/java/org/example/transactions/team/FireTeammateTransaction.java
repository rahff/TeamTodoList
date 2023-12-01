package org.example.transactions.team;


import jakarta.transaction.Transactional;
import org.shared.api.Command;
import org.springframework.stereotype.Service;
import org.team.ports.dto.FireTeammateRequest;
import org.todo.port.dto.DeleteUserTodoListsRequest;



@Service
public class FireTeammateTransaction {

    private final Command<FireTeammateRequest> fireTeammateCommand;
    private final Command<DeleteUserTodoListsRequest> deleteUserTodoListsCommand;

    public FireTeammateTransaction(Command<FireTeammateRequest> fireTeammateCommand, Command<DeleteUserTodoListsRequest> deleteUserTodoListsCommand) {
        this.fireTeammateCommand = fireTeammateCommand;
        this.deleteUserTodoListsCommand = deleteUserTodoListsCommand;
    }

    @Transactional
    public void execute(FireTeammateRequest request){
       fireTeammateCommand.execute(request);
       deleteUserTodoListsCommand.execute(new DeleteUserTodoListsRequest(request.userId()));
    }
}
