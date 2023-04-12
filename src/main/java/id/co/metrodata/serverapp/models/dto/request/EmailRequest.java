package id.co.metrodata.serverapp.models.dto.request;

import lombok.Data;

@Data
public class EmailRequest {

  // private String[] to; // multiple on simpleMailMessageMultiple
  private String to;
  private String subject;
  private String body;
  private String attach;
}
