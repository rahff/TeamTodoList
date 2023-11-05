package todo;


import org.junit.jupiter.api.Test;
import org.query.todo.spi.fakes.InMemoryTodoListDataAccess;
import org.query.todo.api.TodoListDetailsViewQuery;
import org.query.todo.spi.fakes.FakeTodoListDetailsView;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoListDetailsViewQueryTest {

    @Test
    void should_aggregates_data_for_todoListDetails_view(){
        var todoListDataAccess = new InMemoryTodoListDataAccess();
        var view = new TodoListDetailsViewQuery(todoListDataAccess);
        var result = view.query("todoListId");
        assertEquals(FakeTodoListDetailsView.get(), result);
    }

}
