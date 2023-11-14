package adapters.command;

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
    var savedTodoList = createTodoList();
    var todoList = todoListRepository.getTodoListByName(savedTodoList.ref(), savedTodoList.todoListName()).orElse(null);
    assertNotNull(todoList);
    assertEquals(savedTodoList.listId(), todoList.listId());
  }

  @Test
  void getTodoListCount(){
    var savedTodoList = createTodoList();
    var countByUserId = todoListRepository.getTodoListCount(savedTodoList.ref());
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
