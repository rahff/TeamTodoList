package integration.queries;

import integration.controllers.BaseControllerTest;
import org.junit.jupiter.api.Test;

import org.query.todo.spi.fakes.FakeListOfTodoListViewModel;
import org.query.todo.spi.fakes.FakeTodoListDetailsView;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.example.MainTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest(classes = MainTest.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TodoQueryControllerTest extends BaseControllerTest {

    @Test
    void  ListOfTodoListViewModel() throws Exception {
        var expected = objectMapper.writeValueAsString(FakeListOfTodoListViewModel.get());
        mockMvc.perform(get("/todoLists/userId"))
                .andExpect(status().isOk()).andExpect(content().json(expected));
    }
    @Test
    void  todoListDetailsView() throws Exception {
        var expected = objectMapper.writeValueAsString(FakeTodoListDetailsView.get());
        mockMvc.perform(get("/todo-list-details/id"))
                .andExpect(status().isOk()).andExpect(content().json(expected));
    }
}
