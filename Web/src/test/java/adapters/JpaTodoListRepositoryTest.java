package adapters;

import org.example.Main;
import org.example.persistance.entities.TodoList;
import org.example.persistance.repositories.JpaTodoListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import utils.TodoListProvider;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Main.class)
@Profile("prod")
public class JpaTodoListRepositoryTest {

  @Autowired
  private JpaTodoListRepository todoListRepository;

  @Test
  void saveTodoList(){
    var savedTodoList = createTodoList();
    var expectedResult = todoListRepository.findById(savedTodoList.getId()).orElse(null);
    assertNotNull(expectedResult);
    assertEquals(expectedResult.getId(), savedTodoList.getId());
    assertEquals(expectedResult.getTodos().get(0), savedTodoList.getTodos().get(0));
  }

  @Test
  void getTodoListByName(){
    var todoList = todoListRepository.findByUserIdAndName("47108d15-fa3d-4602-a6e4-0b278d63e5ed", "todoListName").orElse(null);
    assertNotNull(todoList);
    assertEquals("22d0f8ce-ab08-4fe7-9593-00810d989c5e", todoList.getId());
  }

  @Test
  void getTodoListCount(){
    var countByUserId = todoListRepository.countByUserId("47108d15-fa3d-4602-a6e4-0b278d63e5ed");
    assertEquals(1, countByUserId);
  }

  @Test
  void deleteTodoList(){
    var todoList = createTodoList();
    todoListRepository.deleteById(todoList.getId());
    var deletedTodoList = todoListRepository.findById(todoList.getId()).orElse(null);
    assertNull(deletedTodoList);
  }

  private TodoList createTodoList(){
    var todoList = TodoListProvider.getTodoListWithOneTodoNotDone();
    return todoListRepository.save(todoList);
  }
}
