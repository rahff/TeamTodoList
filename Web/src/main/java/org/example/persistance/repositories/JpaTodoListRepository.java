package org.example.persistance.repositories;

import org.example.persistance.entities.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaTodoListRepository extends JpaRepository<TodoList, String> {
  Optional<TodoList> findById(String id);
  Optional<TodoList> findByUserIdAndName(String userId, String name);
  int countByUserId(String userId);
}
