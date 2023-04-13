package id.co.metrodata.serverapp.models.Dto.request;

import lombok.Data;

@Data
public class emailReq {
  // private String[] to;
  private String to;
  private String subject;
  private String body;
  private String attach;
}
