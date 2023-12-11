module Security {
  exports org.security.ports.dto;
  exports org.security.ports.spi;
  exports org.security.application;
  exports org.security.exceptions;
    exports org.security.ports.spi.inMemory;
    requires transitive Shared;
}
