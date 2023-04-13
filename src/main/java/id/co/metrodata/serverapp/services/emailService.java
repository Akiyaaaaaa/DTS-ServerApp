package id.co.metrodata.serverapp.services;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import id.co.metrodata.serverapp.models.Dto.request.emailReq;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class emailService {

  private JavaMailSender javaMailSender;
  private SpringTemplateEngine springTemplateEngine;
  private TemplateEngine templateEngine;

  // Simple Mail Message (SMTP)
  public emailReq sendSimpleEmail(emailReq emailReq) {
    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(emailReq.getTo());
    email.setSubject(emailReq.getSubject());
    email.setText(emailReq.getBody());
    javaMailSender.send(email);

    System.out.println();
    System.out.println("Email success to send...");
    System.out.println();
    return emailReq;
  }

  // SMTP Multiple Recipients
  public emailReq sendSimpleEmailMultiple(emailReq emailReq) {
    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(emailReq.getTo());
    email.setSubject(emailReq.getSubject());
    email.setText(emailReq.getBody());
    javaMailSender.send(email);

    System.out.println();
    System.out.println("Email success to send...");
    System.out.println();
    return emailReq;
  }

  // Simple Message With Attachment (MIME)
  // public emailReq sendSimpleEmailWithAttachment(emailReq emailReq) {
  // try {
  // MimeMessage email = javaMailSender.createMimeMessage();
  // MimeMessageHelper help = new MimeMessageHelper(email, true);

  // emailReq.setTemplate("hello.html");

  // Map<String, Object> properties = new HashMap<>();
  // properties.put("name", emailReq.getTo());
  // emailReq.setProperties(properties);

  // Context context = new Context();
  // context.setVariables(emailReq.getProperties());

  // help.setTo(emailReq.getTo());
  // help.setSubject(emailReq.getSubject());
  // help.setText(emailReq.getBody());

  // final String html = springTemplateEngine.process(emailReq.getTemplate(),
  // context);
  // help.setText(html, true);
  // FileSystemResource file = new FileSystemResource(new
  // File(emailReq.getAttach()));
  // help.addAttachment(file.getFilename(), file);

  // javaMailSender.send(email);

  // System.out.println();
  // System.out.println("Email success to send...");
  // System.out.println();
  // } catch (Exception e) {
  // // TODO Auto-generated catch block
  // throw new IllegalStateException("Email failed to send!");
  // }
  // return emailReq;
  // }

  // MIME Message with mMultiple Recipients
  public emailReq sendSimpleEmailWithAttachmenMultipleRecipients(emailReq emailReq) {
    try {
      MimeMessage email = javaMailSender.createMimeMessage();
      MimeMessageHelper help = new MimeMessageHelper(email, true);
      help.setTo(InternetAddress.parse(emailReq.getTo()));
      help.setSubject(emailReq.getSubject());
      help.setText(emailReq.getBody());

      FileSystemResource file = new FileSystemResource(new File(emailReq.getAttach()));
      help.addAttachment(file.getFilename(), file);
      javaMailSender.send(email);
      System.out.println();
      System.out.println("Email success to send...");
      System.out.println();
    } catch (Exception e) {
      throw new IllegalStateException("Email failed to send!");
    }
    return emailReq;
  }

  public emailReq sendSimpleMessageWithHtml(emailReq emailReq) throws MessagingException {
    final Context contex = new Context();
    Map<String, Object> detail = new HashMap<>();
    detail.put("name", emailReq.getTo());
    emailReq.setProperties(detail);
    contex.setVariables(emailReq.getProperties());

    final MimeMessage message = javaMailSender.createMimeMessage();
    final MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
    helper.setSubject(emailReq.getSubject());
    helper.setTo(emailReq.getTo());

    final String htmlform = this.templateEngine.process("hello.html", contex);
    helper.setText(htmlform, true);
    javaMailSender.send(message);

    return emailReq;
  }
  // public emailReq sendTemplate(emailReq emailReq) {
  // try {
  // MimeMessage email = javaMailSender.createMimeMessage();
  // MimeMessageHelper help = new MimeMessageHelper(email, true);
  // emailReq.setTemplate("hello.html");

  // Map<String, Object> properties = new HashMap<>();
  // properties.put("name", emailReq.getTo());
  // emailReq.setProperties(properties);

  // Context context = new Context();
  // context.setVariables(emailReq.getProperties());

  // help.setTo(emailReq.getTo());
  // help.setSubject(emailReq.getSubject());
  // help.setText(emailReq.getBody());

  // String html = springTemplateEngine.process(emailReq.getTemplate(), context);
  // help.setText(html, true);
  // FileSystemResource file = new FileSystemResource(new
  // File(emailReq.getAttach()));

  // help.addAttachment(file.getFilename(), file);

  // javaMailSender.send(email);
  // System.out.println();
  // System.out.println("Email success to send...");
  // System.out.println();
  // } catch (Exception e) {
  // throw new IllegalStateException("Email failed to send!");
  // }
  // return emailReq;
  // }
}
