package org.query.todo.model;

import java.util.List;
import java.util.Objects;

public record TodoList(String id, String name, String createdAt) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoList todoList)) return false;
        return Objects.equals(id, todoList.id) && Objects.equals(name, todoList.name) && Objects.equals(createdAt, todoList.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt);
    }
}
