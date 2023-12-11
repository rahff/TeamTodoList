package org.query.account.api;

import org.query.account.model.AccountDetailsViewModel;
import org.query.account.spi.UserDataAccess;

public class AccountDetailsViewQuery {

  private final UserDataAccess userDataAccess;

  public AccountDetailsViewQuery(UserDataAccess userDataAccess) {
    this.userDataAccess = userDataAccess;
  }

  public AccountDetailsViewModel query(String userId) {
    var user = userDataAccess.getUserById(userId).orElseThrow();
    return new AccountDetailsViewModel(user);
  }
}
