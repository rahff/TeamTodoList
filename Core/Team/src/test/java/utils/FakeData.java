package utils;

import org.shared.dto.UserDto;

import java.util.Optional;

public class FakeData {

    public static UserDto fakeUserDto() {
        return new UserDto("teammateId", "teammate@gmail.com", "Bob", "$$$$$$$$$$$", "TEAMMATE", "accountId", Optional.of("teamId"),Optional.empty());
    }
}
