package org.example.persistance.repositories.todo.springData;

import jakarta.validation.constraints.NotNull;
import org.example.persistance.entities.todo.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaTodoListRepository extends JpaRepository<TodoList, String> {

  Optional<TodoList> findById(String id);
  Optional<TodoList> findByRefAndName(String userId, String name);
  int countByRef(String userId);
  List<TodoList> findByRef(String teamId);
}
