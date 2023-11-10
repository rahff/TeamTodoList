package org.example.service;

import org.shared.spi.DateProvider;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@Profile("test")
public class FakeDateProvider implements DateProvider {
    public Date oneWeekLater() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 11);
        return calendar.getTime();
    }

    public Date oneDayLater() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 5);
        return calendar.getTime();
    }
}
