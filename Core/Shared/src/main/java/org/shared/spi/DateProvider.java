package org.shared.spi;

import java.util.Date;

public interface DateProvider {
    Date oneWeekLater();
    Date oneDayLater();
}
