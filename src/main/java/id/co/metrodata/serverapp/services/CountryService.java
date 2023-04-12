package id.co.metrodata.serverapp.services;

import id.co.metrodata.serverapp.models.Country;
import id.co.metrodata.serverapp.models.Region;
import id.co.metrodata.serverapp.models.dto.request.CountryRequest;
import id.co.metrodata.serverapp.repositories.CountryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CountryService {

  private CountryRepository countryRepository;
  private RegionService regionService;
  private ModelMapper modelMapper;

  public List<Country> getAll() {
    return countryRepository.findAll();
  }

  public Country getById(Integer id) {
    return countryRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Country not found!!!"
        )
      );
  }

  // Without DTO
  public Country create(Country country) {
    if ( // true
      !countryRepository // false => true x false = false
        .findByRegion_Name(country.getRegion().getName())
        .isEmpty()
    ) {
      throw new ResponseStatusException(
        HttpStatus.CONFLICT,
        "Name is already exists on Region!!!"
      );
    }
    // else = false
    return countryRepository.save(country);
  }

  // With DTO
  public Country createWithDTO(CountryRequest countryRequest) {
    Country country = new Country();
    if (
      countryRepository
        .findTopByRegion_Name(countryRequest.getName())
        .isPresent()
    ) {
      throw new ResponseStatusException(
        HttpStatus.CONFLICT,
        "Name is already exists on Region!!!"
      );
    }
    country.setCode(countryRequest.getCode());
    country.setName(countryRequest.getName());

    Region region = regionService.getById(countryRequest.getRegionId());
    country.setRegion(region);

    return countryRepository.save(country);
  }

  // DTO with Model Mapper
  public Country createWithDTOModelMapper(CountryRequest countryRequest) {
    if (
      !countryRepository.findByRegion_Name(countryRequest.getName()).isEmpty()
    ) {
      throw new ResponseStatusException(
        HttpStatus.CONFLICT,
        "Name is already exists on Region!!!"
      );
    }
    Country country = modelMapper.map(countryRequest, Country.class);
    country.setRegion(regionService.getById(countryRequest.getRegionId()));
    return countryRepository.save(country);
  }

  public Country update(Integer id, Country country) {
    getById(id);
    country.setId(id);
    return countryRepository.save(country);
  }

  public Country delete(Integer id) {
    Country country = getById(id);
    countryRepository.delete(country);
    return country;
  }
}
