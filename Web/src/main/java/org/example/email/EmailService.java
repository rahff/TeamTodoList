package org.example.email;

import jakarta.mail.MessagingException;

public interface EmailService {
  void sendJoiningEmail(JoiningMessageParameters parameters) throws MessagingException;
}
