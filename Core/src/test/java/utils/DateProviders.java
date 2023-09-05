package utils;

import java.time.LocalDate;
import java.util.Date;

public class DateProviders {

  public static LocalDate getDateInFuture(){
    return LocalDate.of(2022, 10, 5);
  }

  public static LocalDate now(){
    return LocalDate.of(2022, 8, 7);
  }
}
