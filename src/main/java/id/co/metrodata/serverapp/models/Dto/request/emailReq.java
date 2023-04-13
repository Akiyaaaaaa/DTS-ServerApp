package id.co.metrodata.serverapp.models.Dto.request;

import java.util.Map;

import lombok.Data;

@Data
public class emailReq {
  // private String[] to;
  private String to;
  private String subject;
  private String body;
  private String attach;
  private String template;
  Map<String, Object> properties;
}
