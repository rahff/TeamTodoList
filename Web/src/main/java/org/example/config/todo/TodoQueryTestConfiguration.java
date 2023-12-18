package org.example.config.todo;


import org.query.todo.api.ListOfTodoListViewQuery;
import org.query.todo.api.TodoListDetailsViewQuery;
import org.query.todo.spi.TodoListDataAccess;
import org.query.todo.spi.fakes.InMemoryTodoListDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TodoQueryTestConfiguration {

    @Bean
    TodoListDataAccess todoListDataAccess(){
        return new InMemoryTodoListDataAccess();
    }
    @Bean
    ListOfTodoListViewQuery listOfTodoListViewQuery() {
        return new ListOfTodoListViewQuery(todoListDataAccess());
    }

    @Bean
    TodoListDetailsViewQuery todoListDetailsViewQuery(){
        return new TodoListDetailsViewQuery(todoListDataAccess());
    }
}
