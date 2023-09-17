package org.todo.port.dto;

import java.time.LocalDate;

public record TodoDto(String id,
                      String description,
                      boolean done,
                      LocalDate deadLine,
                      LocalDate createdAt) {
}
