package org.query.todo.spi.fakes;

import org.query.todo.model.ListOfTodoListViewModel;
import org.query.todo.model.TodoList;

import java.util.List;

public class FakeListOfTodoListViewModel {

    public static ListOfTodoListViewModel get() {
        return new ListOfTodoListViewModel(
                List.of(
                        new TodoList("todoListId1", "My TodoList1", "2023-10-14"),
                        new TodoList("todoListId2", "My TodoList2", "2023-10-14"),
                        new TodoList("todoListId3", "My TodoList3", "2023-10-14")
                        )
        );
    }
}
