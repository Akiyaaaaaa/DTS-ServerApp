package id.co.metrodata.serverapp.models.Dto.request;

import lombok.Data;

@Data
public class userReq {
  private String username;
  private String password;
  private String name;
  private String email;
  private String phone;
}