package id.co.metrodata.serverapp.models.Dto.request;

import lombok.Data;

@Data
public class employeeReq {
  private Integer id;
  private String name;
  private String email;
  private String phone;
  private Integer userId;
}
