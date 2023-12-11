package org.todo.port.spi;

import org.todo.port.dto.TodoListDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryTodoListRepository implements TodoListRepository {

    private final List<TodoListDto> data;
    public InMemoryTodoListRepository(){
        this.data = new ArrayList<>(
                List.of(
                        new TodoListDto(
                                "todoListId",
                                "userId",
                                "My todo list",
                                List.of(),
                                LocalDate.of(2022, 8, 7)
                        )
                )
        );
    }

    public InMemoryTodoListRepository(List<TodoListDto> initialData){
        this.data = new ArrayList<>(initialData);
    }
    public void saveTodoList(TodoListDto todoList) {
        var existing = getTodoListById(todoList.listId());
        if(existing.isPresent()){
            data.removeIf(todoListDto -> todoListDto.listId().equals(todoList.listId()));
            data.add(todoList);
        }else data.add(todoList);
    }

    public Optional<TodoListDto> getTodoListByName(String userId, String todoListName) {
        return data.stream().filter(todoListDto -> sameRefAndSameName(todoListDto, userId, todoListName) ).findFirst();
    }

    private boolean sameRefAndSameName(TodoListDto todoListDto, String ref, String name) {
        return todoListDto.todoListName().equals(name) && todoListDto.ref().equals(ref);
    }

    public Optional<TodoListDto> getTodoListById(String todoListId) {
        return data.stream().filter(todoListDto -> todoListDto.listId().equals(todoListId) ).findFirst();
    }

    public int getTodoListCount(String userId) {
        return (int) data.stream().filter(todoListDto -> todoListDto.ref().equals(userId)).count();
    }

    public void delete(String id) {
        data.removeIf(todoListDto -> todoListDto.listId().equals(id));
    }

    public List<TodoListDto> getTodoListsByUserRef(String userId) {
        return data.stream().filter(todoListDto -> todoListDto.ref().equals(userId)).toList();
    }

    public List<TodoListDto> items() {
        return data;
    }
}
