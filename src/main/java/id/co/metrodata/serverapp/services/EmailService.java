package id.co.metrodata.serverapp.services;

import id.co.metrodata.serverapp.models.dto.request.EmailRequest;
import java.io.File;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

  private JavaMailSender javaMailSender;

  public EmailRequest sendSimpleMessage(EmailRequest emailRequest) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(emailRequest.getTo());
    message.setSubject(emailRequest.getSubject());
    message.setText(emailRequest.getBody());
    javaMailSender.send(message);

    System.out.println();
    System.out.println("Email success to send...");
    System.out.println();
    return emailRequest;
  }

  public EmailRequest sendSimpleMessageMultiple(EmailRequest emailRequest) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(emailRequest.getTo()); // with emailRequest = String[] to
    // message.setTo(new String[] { "dts02@yopmail.com", "dts@yopmail.com" });
    message.setSubject(emailRequest.getSubject());
    message.setText(emailRequest.getBody());
    javaMailSender.send(message);

    System.out.println();
    System.out.println("Email success to send...");
    System.out.println();
    return emailRequest;
  }

  public EmailRequest sendMessageWithAttachment(EmailRequest emailRequest) {
    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setTo(emailRequest.getTo());
      helper.setSubject(emailRequest.getSubject());
      helper.setText(emailRequest.getBody());

      FileSystemResource file = new FileSystemResource(
        new File(emailRequest.getAttach())
      );

      helper.addAttachment(file.getFilename(), file);
      javaMailSender.send(message);

      System.out.println();
      System.out.println("Email success to send...");
      System.out.println();
    } catch (Exception e) {
      throw new IllegalStateException("Email failed to send!!!");
    }
    return emailRequest;
  }

  public EmailRequest sendMessageWithAttachmentMultiple(
    EmailRequest emailRequest
  ) {
    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setTo(InternetAddress.parse(emailRequest.getTo()));
      helper.setSubject(emailRequest.getSubject());
      helper.setText(emailRequest.getBody());

      FileSystemResource file = new FileSystemResource(
        new File(emailRequest.getAttach())
      );

      helper.addAttachment(file.getFilename(), file);
      javaMailSender.send(message);

      System.out.println();
      System.out.println("Email success to send...");
      System.out.println();
    } catch (Exception e) {
      throw new IllegalStateException("Email failed to send!!!");
    }
    return emailRequest;
  }
}
