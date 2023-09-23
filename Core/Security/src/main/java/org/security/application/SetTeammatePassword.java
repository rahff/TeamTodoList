package org.security.application;

import org.security.ports.dto.ChangePasswordRequest;
import org.security.ports.spi.ChangePassword;
import org.security.ports.spi.PasswordSecurity;
import org.security.ports.spi.UserRepository;
import org.security.entities.User;
public class SetTeammatePassword implements ChangePassword {
  private final UserRepository userRepository;
  private final PasswordSecurity passwordSecurity;

  public SetTeammatePassword(UserRepository userRepository, PasswordSecurity passwordSecurity) {
    this.userRepository = userRepository;
    this.passwordSecurity = passwordSecurity;
  }

  public void registerNewPassword(ChangePasswordRequest request){
    var teammate = userRepository.findByEmail(request.email()).orElseThrow();
    var user = User.fromDto(teammate);
    user.setPassword(passwordSecurity.encode(request.newPassword()));
    userRepository.save(user.snapshot());
  }
}
