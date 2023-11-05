package todo;

import org.junit.jupiter.api.Test;
import org.query.todo.api.ListOfTodoListViewQuery;
import org.query.todo.spi.fakes.FakeListOfTodoListViewModel;
import org.query.todo.spi.fakes.InMemoryTodoListDataAccess;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfTodoListViewQueryTest {

    @Test
    void should_aggregate_data_for_list_of_todoList_view() {
        var todoListDataAccess = new InMemoryTodoListDataAccess();
        var viewQuery = new ListOfTodoListViewQuery(todoListDataAccess);
        var result = viewQuery.query("userId");
        assertEquals(FakeListOfTodoListViewModel.get(), result);
    }
}
