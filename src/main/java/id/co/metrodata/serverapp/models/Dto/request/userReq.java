package id.co.metrodata.serverapp.models.Dto.request;

import java.util.List;
import lombok.Data;

@Data
public class userReq {
  private Integer id;
  private String name;
  private String email;
  private String phone;
  private List<String> roles;
}