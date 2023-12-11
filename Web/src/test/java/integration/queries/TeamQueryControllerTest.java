package integration.queries;

import integration.controllers.BaseControllerTest;
import org.example.MainTest;
import org.junit.jupiter.api.Test;
import org.query.team.model.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;


import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = MainTest.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TeamQueryControllerTest extends BaseControllerTest {
    @Test
    void  teamDetailsView() throws Exception {
        var expected = objectMapper.writeValueAsString(new FakeTeamDetailsViewModel().get());
        mockMvc.perform(get("/team-details/teamId1"))
                .andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    void  teamListView() throws Exception {
        var expected = objectMapper.writeValueAsString(new FakeTeamListViewModel().get());
        mockMvc.perform(get("/teams/accountId"))
                .andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    void  teammateListView() throws Exception {
        var expected = objectMapper.writeValueAsString(new FakeTeammateListViewModel().get());
        mockMvc.perform(get("/teammates/accountId"))
                .andExpect(status().isOk()).andExpect(content().json(expected));
    }
}

class FakeTeamListViewModel {

    public TeamListViewModel get() {
        return new TeamListViewModel(
                List.of(
                        new TeamCard("teamId1", "Team1", 2, 2),
                        new TeamCard("teamId2", "Team2", 2, 2)
                ),
                List.of(new Teammate("id_teammate3",
                                "teammateNameid_teammate3",
                                "teammateid_teammate3@gmail.com", null),
                        new Teammate("id_teammate4", "teammateNameid_teammate4",
                                "teammateid_teammate4@gmail.com", null))
        );
    }
}

class FakeTeammateListViewModel {

    public TeammateListViewModel get(){
        return new TeammateListViewModel(
                List.of(
                        new Teammate("teammateId1", "teammateName1", "teammateName1@gmail.com","teamId1"),
                        new Teammate("teammateId2", "teammateName2", "teammateName2@gmail.com", "teamId1"),
                        new Teammate("teammateId3", "teammateName3", "teammateName3@gmail.com", "teamId2"),
                        new Teammate("teammateId4", "teammateName4", "teammateName4@gmail.com", "teamId2")
                ));
    }
}

class FakeTeamDetailsViewModel {

    public TeamDetailsViewModel get() {
        return new TeamDetailsViewModel(
                new TeamDetails("teamId1", "Team1",
                        List.of(new Teammate("id_teammate1",
                                        "teammateNameid_teammate1",
                                        "teammateid_teammate1@gmail.com", "teamId1"),
                                new Teammate("id_teammate2", "teammateNameid_teammate2",
                                        "teammateid_teammate2@gmail.com", "teamId1")),
                        List.of(
                                new TodoList("todoListId1", "AtodoList", "2023-10-14"),
                                new TodoList("todoListId2", "OthertodoList", "2023-10-17")
                        )), List.of(new Teammate("id_teammate3",
                        "teammateNameid_teammate3",
                        "teammateid_teammate3@gmail.com", null),
                new Teammate("id_teammate4", "teammateNameid_teammate4",
                        "teammateid_teammate4@gmail.com", null))
        );
    }
}

