package id.co.metrodata.serverapp.services;

import id.co.metrodata.serverapp.models.Country;
import id.co.metrodata.serverapp.models.Region;
import id.co.metrodata.serverapp.models.dto.request.CountryRequest;
import id.co.metrodata.serverapp.repositories.CountryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
// @NoArgsConstructor
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
    return countryRepository.save(country);
  }

  // With DTO
  public Country createWithDTO(CountryRequest countryRequest) {
    Country country = new Country();
    country.setCode(countryRequest.getCode());
    country.setName(countryRequest.getName());

    Region region = regionService.getById(countryRequest.getRegionId());
    country.setRegion(region);

    return countryRepository.save(country);
  }

  // DTO with Model Mapper
  public Country createWithDTOModelMapper(CountryRequest countryRequest) {
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
