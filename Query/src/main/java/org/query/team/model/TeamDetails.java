package org.query.team.model;



import java.util.List;
import java.util.Objects;

public record TeamDetails(String id, String name, List<Teammate> teammates, List<TodoList> todoLists) {

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamDetails that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(teammates, that.teammates) && Objects.equals(todoLists, that.todoLists);
    }


    public int hashCode() {
        return Objects.hash(id, name, teammates, todoLists);
    }
}
