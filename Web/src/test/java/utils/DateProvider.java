package utils;

import java.time.LocalDate;

public class DateProvider {

  public static LocalDate now(){
    return LocalDate.of(2023, 9, 17);
  }

  public static LocalDate dateInFuture(){
    return LocalDate.of(2023, 11, 5);
  }
}
