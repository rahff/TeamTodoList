package org.example.persistance.repositories.todo.command;

import org.example.persistance.entities.todo.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaTodoListRepository extends JpaRepository<TodoList, String> {
  Optional<TodoList> findById(String id);
  Optional<TodoList> findByUserIdAndName(String userId, String name);
  int countByUserId(String userId);
}
