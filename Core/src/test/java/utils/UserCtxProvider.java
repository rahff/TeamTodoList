package utils;

import org.core.port.dto.UserContext;
import org.core.port.dto.UserPlan;


public class UserCtxProvider {

  public static UserContext freeUser() {
    return new UserContext("userId", UserPlan.FREE);
  }

  public static UserContext premiumUser() {
    return new UserContext("userIdPremium", UserPlan.PREMIUM);
  }
}
