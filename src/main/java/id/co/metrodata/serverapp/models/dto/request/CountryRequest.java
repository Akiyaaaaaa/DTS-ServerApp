package id.co.metrodata.serverapp.models.dto.request;

import lombok.Data;

@Data
public class CountryRequest {

  private String code;
  private String name;
  private Integer regionId;
}
