package org.example.transactions.security;


import jakarta.transaction.Transactional;
import org.security.ports.dto.ChangePasswordRequest;
import org.security.ports.spi.ChangePassword;
import org.springframework.stereotype.Service;

@Service
public class SetPasswordTransaction {
  private final ChangePassword changePassword;

  public SetPasswordTransaction(ChangePassword changePassword) {
    this.changePassword = changePassword;
  }

  @Transactional
  public void execute(ChangePasswordRequest request){
    changePassword.registerNewPassword(request);
  }
}
