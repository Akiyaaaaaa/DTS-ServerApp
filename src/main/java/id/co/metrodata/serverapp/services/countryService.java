package id.co.metrodata.serverapp.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverapp.models.country;
import id.co.metrodata.serverapp.models.region;
import id.co.metrodata.serverapp.models.Dto.request.countryReq;
import id.co.metrodata.serverapp.repositories.countryRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class countryService {
  private countryRepo countryRepo;
  private regionService regionService;
  private ModelMapper modelMapper;

  public List<country> getAll() {
    return countryRepo.findAll();
  }

  public country getById(Integer id) {
    return countryRepo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not exist!"));
  }

  // without DTO
  public country insert(country country) {
    if (!countryRepo.findByRegionName(country.getRegion().getName()).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "The same name is found in Region");
    }
    return countryRepo.save(country);
  }

  // with DTO
  public country insertWithDTO(countryReq countryReq) {
    country country = new country();
    if (countryRepo.findTopByRegionName(countryReq.getName()).isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "The same name is found in Region");
    }
    country.setCode(countryReq.getCode());
    country.setName(countryReq.getName());

    region region = regionService.getById(countryReq.getRegionId());
    country.setRegion(region);
    return countryRepo.save(country);
  }

  // DTO with Model Mapper
  public country insertWithDTOModelMapper(countryReq countryReq) {
    if (!countryRepo.findByRegionName(countryReq.getName()).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "The same name is found in Region");
    }
    country country = modelMapper.map(countryReq, country.class);
    country.setRegion(regionService.getById(countryReq.getRegionId()));
    return countryRepo.save(country);
  }

  public country update(Integer id, country country) {
    getById(id);
    country.setId(id);
    return countryRepo.save(country);
  }

  public country delete(Integer id) {
    country country = getById(id);
    countryRepo.delete(country);
    return country;
  }
}
