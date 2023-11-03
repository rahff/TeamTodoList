package utils;

import org.security.ports.dto.UserDto;

public class FakeData {

    public static UserDto fakeUserDto() {
        return new UserDto("teammateId", "teammate@gmail.com", "Bob", "$$$$$$$$$$$", "TEAMMATE", "accountId");
    }
}
