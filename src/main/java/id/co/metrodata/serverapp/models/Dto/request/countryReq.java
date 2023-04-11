package id.co.metrodata.serverapp.models.Dto.request;

import lombok.Data;

@Data
public class countryReq {
  private String code;
  private String name;
  private Integer regionId;
}
