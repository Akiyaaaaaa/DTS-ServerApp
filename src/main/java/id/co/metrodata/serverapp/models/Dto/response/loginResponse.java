package id.co.metrodata.serverapp.models.Dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class loginResponse {
  private String username;
  private String email;
  private List<String> authorities;
}
