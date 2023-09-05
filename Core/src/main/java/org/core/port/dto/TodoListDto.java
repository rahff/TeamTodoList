package org.core.port.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record TodoListDto(String listId, String userId, String todoListName, List<TodoDto> list) {

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TodoListDto that = (TodoListDto) o;
    return Objects.equals(listId, that.listId)
      && Objects.equals(userId, that.userId)
      && Objects.equals(todoListName, that.todoListName)
      && Arrays.deepEquals(list.toArray(), that.list.toArray());
  }

  @Override
  public int hashCode() {
    return Objects.hash(listId, userId, todoListName);
  }
}
