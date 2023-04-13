package id.co.metrodata.serverapp.controllers;

import javax.mail.MessagingException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.metrodata.serverapp.models.Dto.request.emailReq;
import id.co.metrodata.serverapp.services.emailService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
public class emailController {

  private emailService emailService;

  @PostMapping("/simple")
  public emailReq sendSimpleEmail(@RequestBody emailReq emailReq) {
    return emailService.sendSimpleEmail(emailReq);
  }

  @PostMapping("/simple-multi")
  public emailReq sendSimpleEmailMultiple(@RequestBody emailReq emailReq) {
    return emailService.sendSimpleEmailMultiple(emailReq);
  }

  @PostMapping("/attach")
  public emailReq sendSimpleEmailWithAttachment(
      @RequestBody emailReq emailReq) throws MessagingException {
    // return emailService.sendSimpleEmailWithAttachment(emailReq);
    return emailService.sendSimpleMessageWithHtml(emailReq);
  }

  @PostMapping("/attach-multi")
  public emailReq sendSimpleEmailWithAttachmenMultipleRecipients(
      @RequestBody emailReq emailReq) {
    return emailService.sendSimpleEmailWithAttachmenMultipleRecipients(emailReq);
  }

  // @PostMapping("/html")
  // public emailReq sendTemplate(
  // @RequestBody emailReq emailReq) {
  // return emailService.sendTemplate(emailReq);
  // }
}
