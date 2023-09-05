package org.core.port.api;

import org.core.port.dto.CreateTodoListRequest;

public interface CreateTodoList {
  void execute(CreateTodoListRequest request);
}
