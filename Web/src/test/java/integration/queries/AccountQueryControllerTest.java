package integration.queries;

import integration.controllers.BaseControllerTest;
import org.example.MainTest;
import org.junit.jupiter.api.Test;
import org.query.account.dto.UserDto;
import org.query.account.model.AccountDetailsViewModel;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = MainTest.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AccountQueryControllerTest extends BaseControllerTest {

    @Test
    void accountView() throws Exception {
        var expected = objectMapper.writeValueAsString(new AccountDetailsViewModel(new UserDto("userId", "User1", "useremail@gmail.com", "TEAMMATE", "accountId1")));
        mockMvc.perform(get("/account/userId"))
                .andExpect(status().isOk()).andExpect(content().json(expected));
    }
}
