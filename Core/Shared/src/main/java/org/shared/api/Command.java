package org.shared.api;

public interface Command<T> {
  void execute(T request);
}
