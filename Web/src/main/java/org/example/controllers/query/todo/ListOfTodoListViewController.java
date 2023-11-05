package org.example.controllers.query.todo;

import org.query.todo.api.ListOfTodoListViewQuery;
import org.query.todo.model.ListOfTodoListViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class ListOfTodoListViewController {

    private final ListOfTodoListViewQuery listOfTodoListViewQuery;

    public ListOfTodoListViewController(ListOfTodoListViewQuery listOfTodoListViewQuery) {
        this.listOfTodoListViewQuery = listOfTodoListViewQuery;
    }

    @GetMapping("/todoLists/{userId}")
    public ListOfTodoListViewModel listOfTodoListViewModel(@PathVariable String userId){
        try{
            return listOfTodoListViewQuery.query(userId);
        }catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
