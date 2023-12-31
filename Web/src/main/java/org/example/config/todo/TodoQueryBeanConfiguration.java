package org.example.config.todo;


import org.query.todo.api.ListOfTodoListViewQuery;
import org.query.todo.api.TodoListDetailsViewQuery;
import org.query.todo.spi.TodoListDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class TodoQueryBeanConfiguration {

    @Autowired
    private TodoListDataAccess todoListDataAccess;

    @Bean
    ListOfTodoListViewQuery listOfTodoListViewQuery() {
        return new ListOfTodoListViewQuery(todoListDataAccess);
    }

    @Bean
    TodoListDetailsViewQuery todoListDetailsViewQuery(){
        return new TodoListDetailsViewQuery(todoListDataAccess);
    }
}
