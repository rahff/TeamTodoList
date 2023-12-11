package org.example.transactions.security;


import jakarta.transaction.Transactional;
import org.security.application.SetTeammatePassword;
import org.security.ports.dto.ChangePasswordRequest;


public class SetPasswordTransaction {
  private final SetTeammatePassword changePassword;

  public SetPasswordTransaction(SetTeammatePassword changePassword) {
    this.changePassword = changePassword;
  }

  @Transactional
  public void execute(ChangePasswordRequest request){
    changePassword.registerNewPassword(request);
  }
}
