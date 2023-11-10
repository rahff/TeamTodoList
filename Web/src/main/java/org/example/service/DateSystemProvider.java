package org.example.service;

import org.shared.spi.DateProvider;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Profile("prod")
public class DateSystemProvider implements DateProvider {

    public Date oneWeekLater() {
        return null;
    }

    public Date oneDayLater() {
        long DAY = 24 * 60 * 60 * 1000;
        long oneDayLaterTimeMillis = System.currentTimeMillis() + DAY;
        return new Date(oneDayLaterTimeMillis);
    }
}
