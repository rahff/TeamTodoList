package utils;

import org.todo.port.dto.UserContext;
import org.todo.port.dto.UserPlan;


public class UserCtxProvider {

  public static UserContext freeUser() {
    return new UserContext("userId", UserPlan.FREE);
  }

  public static UserContext premiumUser() {
    return new UserContext("userIdPremium", UserPlan.PREMIUM);
  }
}
