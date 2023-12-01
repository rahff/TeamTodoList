package org.example.controllers.query.todo;

import org.query.todo.api.TodoListDetailsViewQuery;
import org.query.todo.model.TodoListDetailsViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;


@RestController
public class TodoListDetailsViewController {
    private final TodoListDetailsViewQuery viewQuery;

    public TodoListDetailsViewController(TodoListDetailsViewQuery viewQuery) {
        this.viewQuery = viewQuery;
    }

    @GetMapping("/todo-list-details/{id}")
    public TodoListDetailsViewModel todoListDetailsViewModel(@PathVariable String id){
        try{
            return viewQuery.query(id);
        }catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
