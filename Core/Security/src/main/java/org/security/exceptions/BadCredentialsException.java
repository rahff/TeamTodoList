package org.security.exceptions;

public class BadCredentialsException extends RuntimeException{
  public BadCredentialsException() {
    super("Bad credentials");
  }
}
