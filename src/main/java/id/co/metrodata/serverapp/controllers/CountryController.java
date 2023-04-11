package id.co.metrodata.serverapp.controllers;

import id.co.metrodata.serverapp.models.Country;
import id.co.metrodata.serverapp.models.dto.request.CountryRequest;
import id.co.metrodata.serverapp.services.CountryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/country")
public class CountryController {

  private CountryService countryService;

  @GetMapping
  public List<Country> getAll() {
    return countryService.getAll();
  }

  @GetMapping("/{id}")
  public Country getById(@PathVariable Integer id) {
    return countryService.getById(id);
  }

  // Without DTO
  @PostMapping
  public Country create(@RequestBody Country country) {
    return countryService.create(country);
  }

  // With DTO
  @PostMapping("/dto")
  public Country createWithDTO(@RequestBody CountryRequest countryRequest) {
    return countryService.createWithDTO(countryRequest);
  }

  // DTO with Model Mapper
  @PostMapping("/dto-mm")
  public Country createWithDTOModelMapper(
    @RequestBody CountryRequest countryRequest
  ) {
    return countryService.createWithDTOModelMapper(countryRequest);
  }

  @PutMapping("/{id}")
  public Country update(
    @PathVariable Integer id,
    @RequestBody Country country
  ) {
    return countryService.update(id, country);
  }

  @DeleteMapping("/{id}")
  public Country delete(@PathVariable Integer id) {
    return countryService.delete(id);
  }
}
