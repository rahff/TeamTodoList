package utils;

import java.util.UUID;

public class StringProvider {

  public static String unique() {
    return UUID.randomUUID().toString();
  }
}
