package org.query.todo.spi.fakes;



import org.query.todo.model.TodoList;
import org.query.todo.model.Todo;
import org.query.todo.model.TodoListDetailsViewModel;

import java.util.List;

public class FakeTodoListDetailsView {

    public static TodoListDetailsViewModel get(){
        return new TodoListDetailsViewModel(
                new TodoList("todoListId1", "MyTodoList", "2023-09-12"),
                List.of(new Todo("todo1ofTodoList1_id", "do something", false, "2023-12-14", "2023-11-30"),
                        new Todo("todo2ofTodoList1_id", "do something else", false, "2023-12-14", "2023-11-30"),
                        new Todo("todo3ofTodoList1_id", "do something good", false, "2023-12-14", "2023-11-30")
                )
        );
    }
}
