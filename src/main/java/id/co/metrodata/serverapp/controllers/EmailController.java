package id.co.metrodata.serverapp.controllers;

import id.co.metrodata.serverapp.models.dto.request.EmailRequest;
import id.co.metrodata.serverapp.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
public class EmailController {

  private EmailService emailService;

  @PostMapping("/simple")
  public EmailRequest sendSimpleMessage(
    @RequestBody EmailRequest emailRequest
  ) {
    return emailService.sendSimpleMessage(emailRequest);
  }

  @PostMapping("/simple-multiple")
  public EmailRequest sendSimpleMessageMultiple(
    @RequestBody EmailRequest emailRequest
  ) {
    return emailService.sendSimpleMessageMultiple(emailRequest);
  }

  @PostMapping("/attach")
  public EmailRequest sendMessageWithAttachment(
    @RequestBody EmailRequest emailRequest
  ) {
    return emailService.sendMessageWithAttachment(emailRequest);
  }

  @PostMapping("/attach-multiple")
  public EmailRequest sendMessageWithAttachmentMultiple(
    @RequestBody EmailRequest emailRequest
  ) {
    return emailService.sendMessageWithAttachmentMultiple(emailRequest);
  }
}
