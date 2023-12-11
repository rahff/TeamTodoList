package org.team.ports.spi.inMemory;

import org.team.ports.dto.TeamDto;
import org.team.ports.spi.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryTeamRepository implements TeamRepository {

    private List<TeamDto> data;

    public InMemoryTeamRepository(){
        this.data = new ArrayList<>(List.of(new TeamDto("teamId", "Team1", List.of(), "accountId")));
    }
    public InMemoryTeamRepository(List<TeamDto> initialData){
        this.data = new ArrayList<>(initialData);
    }

    public void setData(List<TeamDto> data) {
        this.data = new ArrayList<>(data);
    }
    public void saveTeam(TeamDto teamDto) {
        var existing = getTeamById(teamDto.id());
        if(existing.isPresent()) {
            data.removeIf(team -> team.id().equals(teamDto.id()));
            data.add(teamDto);
        }
        data.add(teamDto);
    }


    public Optional<TeamDto> getTeamById(String teamId) {
        return data.stream().filter(teamDto -> teamDto.id().equals(teamId)).findFirst();
    }


    public void deleteTeam(String teamId) {
        data.removeIf(team -> team.id().equals(teamId));
    }

    public List<TeamDto> items() {
        return data;
    }
}
