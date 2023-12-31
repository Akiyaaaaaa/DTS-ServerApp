package id.co.metrodata.serverapp.models.dto.request;

import lombok.Data;

@Data
public class UserRequest {

  private String username;
  private String password;
  private String name;
  private String email;
  private String phone;
}
