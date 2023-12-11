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
}
