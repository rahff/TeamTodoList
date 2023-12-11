package org.team.ports.spi.inMemory;

import org.shared.dto.UserDto;
import org.team.ports.spi.TeammateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryTeammateRepository implements TeammateRepository {

    private List<TeammateDto> data;

    public InMemoryTeammateRepository(){

        this.data = new ArrayList<>(List.of(new TeammateDto(
                "teammate1Id",
                "accountId",
                Optional.empty()
        )));
    }
    public InMemoryTeammateRepository(List<UserDto> initialData){
        this.data = new ArrayList<>(mapToTeammates(initialData));
    }

    private List<TeammateDto> mapToTeammates(List<UserDto> userDtoList ) {
        var mapping = userDtoList.stream().map(userDto -> new TeammateDto(userDto.id(), userDto.accountId(), Optional.empty())).toList();
        return new ArrayList<>(mapping);
    }

    private TeammateDto mapToTeammate(UserDto userDto ) {
        return new TeammateDto(userDto.id(), userDto.accountId(), Optional.empty());
    }

    public void saveUserAsTeammate(UserDto userDto) {
        data.add(mapToTeammate(userDto));
    }


    public void addTeamIdOnTeammate(List<String> ids, String teamId) {
        ids.forEach(id -> this.data = new ArrayList<>(data.stream().map(teammate -> {
            if(teammate.ref().equals(id)){
                return new TeammateDto(teammate.ref(), teammate.accountId(), Optional.of(teamId));
            }else return teammate;
        }).toList()));
    }


    public void removeTeammateUser(String userId) {
        data.removeIf(teammateDto -> teammateDto.ref().equals(userId));
    }
    public List<TeammateDto> items() {
        return data;
    }

    public record TeammateDto(String ref, String accountId, Optional<String> teamId) {
        public boolean equals(Object o) {
            if (!(o instanceof TeammateDto that)) return false;
            if (!ref.equals(that.ref)) return false;
            if (!accountId.equals(that.accountId)) return false;
            return teamId.equals(that.teamId);
        }

        public int hashCode() {
            return Objects.hash(ref, accountId, teamId);
        }
    }
}
