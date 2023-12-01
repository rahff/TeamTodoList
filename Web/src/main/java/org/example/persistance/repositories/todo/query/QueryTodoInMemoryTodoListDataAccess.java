package org.example.persistance.repositories.todo.query;


import org.query.todo.dto.TodoListDto;
import org.query.todo.model.Todo;
import org.query.todo.spi.TodoListDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("test")
public class QueryTodoInMemoryTodoListDataAccess implements TodoListDataAccess {

    public Optional<TodoListDto> getTodoListById(String id) {
        return Optional.of(new TodoListDto("todoListId1", "MyTodoList", "2023-09-12", List.of(new Todo("todo1ofTodoList1_id", "do something", false, "2023-12-14", "2023-11-30"),
                new Todo("todo2ofTodoList1_id", "do something else", false, "2023-12-14", "2023-11-30"),
                new Todo("todo3ofTodoList1_id", "do something good", false, "2023-12-14", "2023-11-30")
        )));
    }


    public List<TodoListDto> getUserTodoList(String userId) {
        return List.of(
                new TodoListDto("todoListId1", "My TodoList1", "2023-10-14", List.of()),
                new TodoListDto("todoListId2", "My TodoList2", "2023-10-14", List.of()),
                new TodoListDto("todoListId3", "My TodoList3", "2023-10-14", List.of())
        );
    }
}
