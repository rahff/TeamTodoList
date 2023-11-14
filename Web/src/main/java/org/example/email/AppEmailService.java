package org.example.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
@Profile("prod")
public class AppEmailService implements  EmailService {

  @Value("${hostname.domain.login}")
  private String loginLink;
  private final JavaMailSender mailSender;
  private ExecutorService executorService;

  public AppEmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void sendJoiningEmail(JoiningMessageParameters parameters) throws MessagingException {
    try{
      executorService = Executors.newSingleThreadExecutor();
      var message = createMessage(parameters.managerName());
      formatMessage(message, parameters);
      executorService.submit(() ->  mailSender.send(message));
    }finally {
      executorService.shutdown();
    }
  }

  private MimeMessage createMessage(String managerName) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    message.setSubject(managerName + " invite you to join his team");
    return message;
  }

  private void formatMessage(MimeMessage message, JoiningMessageParameters parameters) throws MessagingException {
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
    helper.setTo(parameters.email());
    helper.setText(
      "<html>\n" +
        "<head>\n" +
        "  <meta charset=\"UTF-8\">\n" +
        "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "  <title>Document</title>\n" +
        "</head>\n" +
        "<p style='text-align: center;'>" +"Hello "+ parameters.teammateName() + ",</p>\n" +
        "<p style='text-align: center;'>" + parameters.managerName() + " want to you join his team at todo-online.com !</p>\n" +
        "<p style='text-align: center;'>Please login at <a href='"+ loginLink + "'>"+loginLink+"</a> with your temporary password :</p>\n" +
        "<p style='text-align: center;'>"+parameters.code()+"</p>\n" +
        "</html>", true);
  }
}
