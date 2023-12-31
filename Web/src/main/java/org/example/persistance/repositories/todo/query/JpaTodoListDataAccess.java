package org.example.persistance.repositories.todo.query;


import org.query.todo.dto.TodoListDto;
import org.query.todo.spi.TodoListDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Profile("prod")
public class JpaTodoListDataAccess implements TodoListDataAccess {

    public Optional<TodoListDto> getTodoListById(String id) {
        return Optional.empty();
    }

    @Override
    public List<TodoListDto> getUserTodoList(String userId) {
        return null;
    }
}
