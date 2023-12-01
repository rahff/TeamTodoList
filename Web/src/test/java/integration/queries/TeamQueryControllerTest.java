package integration.queries;

import integration.controllers.BaseControllerTest;
import org.example.MainTest;
import org.junit.jupiter.api.Test;
import org.query.team.spi.fakes.FakeTeamDetailsViewModel;
import org.query.team.spi.fakes.FakeTeamListViewModel;
import org.query.team.spi.fakes.FakeTeammateListViewModel;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = MainTest.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TeamQueryControllerTest extends BaseControllerTest {
    @Test
    void  teamDetailsView() throws Exception {
        var expected = objectMapper.writeValueAsString(FakeTeamDetailsViewModel.get());
        mockMvc.perform(get("/team-details/teamId1"))
                .andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    void  teamListView() throws Exception {
        var expected = objectMapper.writeValueAsString(FakeTeamListViewModel.get());
        mockMvc.perform(get("/teams/accountId"))
                .andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    void  teammateListView() throws Exception {
        var expected = objectMapper.writeValueAsString(FakeTeammateListViewModel.get());
        mockMvc.perform(get("/teammates/accountId"))
                .andExpect(status().isOk()).andExpect(content().json(expected));
    }
}
