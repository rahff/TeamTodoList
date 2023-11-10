package org.example.persistance.repositories.security.command;


import org.shared.dto.UserDto;
import org.shared.spi.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("test")
public class UserInMemoryRepository implements UserRepository {
  private final List<UserDto> data;

  public UserInMemoryRepository() {
    data = new ArrayList<>();
    data.addAll(List.of(UserProvider.manager(), UserProvider.teammate(), UserProvider.teammate2()));

  }

  public Optional<UserDto> findByEmail(String email) {
    return data.stream().filter(userDto -> userDto.email().equals(email)).findFirst();
  }


  public UserDto save(UserDto newUser) {
    var existing = findByEmail(newUser.email()).orElse(null);
    if(existing != null){
      data.removeIf((userDto -> userDto.email().equals(newUser.email())));
      data.add(newUser);
      return newUser;
    }
    data.add(newUser);
    return newUser;
  }
}


class UserProvider {

  private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  public static UserDto manager(){
    return new UserDto("userManagerId", "manager@gmail.com", "Rahff", passwordEncoder.encode("12345"), "MANAGER", "accountId");
  }

  public static UserDto teammate(){
    return new UserDto("userId", "teammate@gmail.com", "Mikki", passwordEncoder.encode("12345"), "TEAMMATE", "accountId");
  }

  public static UserDto teammate2(){
    return new UserDto("userId2", "teammate2@gmail.com", "Jacques", passwordEncoder.encode("12345"), "TEAMMATE", "accountId");
  }
}