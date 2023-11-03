package adapters;

import org.example.Main;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.todo.port.dto.TodoListDto;
import org.todo.port.spi.TodoListRepository;
import utils.TodoListProvider;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Main.class)
@Profile("prod")
public class JpaTodoListRepositoryTest {

  @Autowired
  private TodoListRepository todoListRepository;

  @Test
  void saveTodoList(){
    var savedTodoList = createTodoList();
    var expectedResult = todoListRepository.getTodoListById(savedTodoList.listId()).orElse(null);
    assertNotNull(expectedResult);
    assertEquals(expectedResult.listId(), savedTodoList.listId());
    assertEquals(expectedResult.list().get(0), savedTodoList.list().get(0));
  }

  @Test
  void getTodoListByName(){
    var todoList = todoListRepository.getTodoListByName("c4f90bb0-2b2a-49dd-a20b-ac127a94ca94", "bd1add73-ce3d-4c7a-af89-a12322e5ab7e").orElse(null);
    assertNotNull(todoList);
    assertEquals("6566ba7b-1753-4187-af1d-d92ed0977934", todoList.listId());
  }

  @Test
  void getTodoListCount(){
    var countByUserId = todoListRepository.getTodoListCount("d988af63-1ab5-41fa-9318-8e8586639f25");
    assertEquals(1, countByUserId);
  }

  @Test
  void deleteTodoList(){
    var todoList = createTodoList();
    todoListRepository.delete(todoList.listId());
    var deletedTodoList = todoListRepository.getTodoListById(todoList.listId()).orElse(null);
    assertNull(deletedTodoList);
  }

  private TodoListDto createTodoList(){
    var todoList = TodoListProvider.getTodoListWithOneTodoNotDone().toDto();
    todoListRepository.saveTodoList(todoList);
    return todoList;
  }
}
