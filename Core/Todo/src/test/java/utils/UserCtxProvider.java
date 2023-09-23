package utils;

import org.todo.port.dto.UserContext;
import org.todo.port.dto.UseRole;


public class UserCtxProvider {

  public static UserContext freeUser() {
    return new UserContext("userId", UseRole.TEAMMATE);
  }

  public static UserContext premiumUser() {
    return new UserContext("userIdPremium", UseRole.MANAGER);
  }
}
