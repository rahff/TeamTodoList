package org.example.controllers.api.team;

import org.example.controllers.api.todo.jsonPayloads.response.IdJson;
import org.example.transactions.team.FireTeammateTransaction;
import org.example.transactions.todo.DeleteUserTodoListsTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.team.ports.dto.FireTeammateRequest;
import org.todo.port.dto.DeleteUserTodoListsRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class FireTeammateController {

    private final FireTeammateTransaction fireTeammateTransaction;

    public FireTeammateController(FireTeammateTransaction fireTeammateTransaction) {
        this.fireTeammateTransaction = fireTeammateTransaction;
    }

    @PutMapping("/fire-teammate")
    public IdJson fireTeammate(@RequestBody FireTeammateRequest request) {
        try{
            fireTeammateTransaction.execute(request);
            return new IdJson(request.userId());
        }catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
    }
}
