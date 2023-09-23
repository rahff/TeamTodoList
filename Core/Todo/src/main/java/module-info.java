module Todo {
  exports org.todo.port.dto;
  exports org.todo.port.spi;
  exports org.todo.application.commands;
  exports org.todo.application.exceptions;
  requires transitive Shared;
}
