package org.core.port.dto;

import java.time.LocalDate;
import java.util.Date;

public record TodoDto( String description,
                       boolean done,
                       LocalDate deadLine,
                       LocalDate createdAt) {
}
