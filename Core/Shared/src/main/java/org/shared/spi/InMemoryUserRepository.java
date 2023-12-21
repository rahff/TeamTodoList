package org.shared.spi;

import org.shared.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository{

    private List<UserDto> data;

    public InMemoryUserRepository(){
        this.data = new ArrayList<>(List.of(
                new UserDto(
                        "teammateId",
                        "rahff@gmail.com",
                        "rahff",
                        "12345",
                        "TEAMMATE",
                        "accountId",
                        Optional.empty(),
                        Optional.empty())
        ));
    }
    public InMemoryUserRepository(List<UserDto> initialData){
        this.data = new ArrayList<>(initialData);
    }
    public Optional<UserDto> findByEmail(String email) {
        return data.stream().filter(userDto -> userDto.email().equals(email)).findFirst();
    }

    public UserDto save(UserDto newUser) {
        var existing = findByEmail(newUser.email());
        if(existing.isPresent()){
            data.removeIf(userDto -> userDto.email().equals(newUser.email()));
            data.add(newUser);
            return newUser;
        }else data.add(newUser);
        return newUser;
    }

    public List<UserDto> items() {
        return data;
    }
    public Optional<UserDto> findBySubscription(String subscriptionId){
        return data.stream().filter(userDto -> bySubscriptionId(userDto, subscriptionId)).findFirst();
    }

    public Optional<UserDto> findByAccountIdAndTeamIdIsNull(String accountId) {
        return data.stream().filter(userDto -> userDto.accountId().equals(accountId) && userDto.teamId().isEmpty()).findFirst();
    }

    public Optional<UserDto> findById(String teammateId) {
        return  data.stream().filter(userDto -> userDto.id().equals(teammateId)).findFirst();
    }

    public void addTeamIdOnTeammate(List<String> theirIds, String teamId) {

        theirIds.forEach(id -> {

            var user = findById(id).orElse(null);
            if(data.removeIf(userDto -> userDto.id().equals(id))){
                data.add(new UserDto(
                        user.id(),
                        user.email(),
                        user.name(),
                        user.password(),
                        user.role(),
                        user.accountId(),
                        Optional.of(teamId),
                        user.subscription()));
            }
        });
    }


    public void removeTeammateUser(String teammateId) {
        data.removeIf(userDto -> userDto.id().equals(teammateId));
    }

    private boolean bySubscriptionId(UserDto userDto, String subscriptionId) {
        if(userDto.subscription().isPresent()){
            return userDto.subscription().get().id().equals(subscriptionId);
        }
        return false;
    }
}
