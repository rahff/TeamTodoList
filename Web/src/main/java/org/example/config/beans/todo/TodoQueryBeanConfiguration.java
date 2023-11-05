package org.example.config.beans.todo;


import org.query.todo.api.ListOfTodoListViewQuery;
import org.query.todo.api.TodoListDetailsViewQuery;
import org.query.todo.spi.TodoListDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
