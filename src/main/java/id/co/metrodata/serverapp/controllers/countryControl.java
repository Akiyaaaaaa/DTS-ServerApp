package id.co.metrodata.serverapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import id.co.metrodata.serverapp.models.country;
import id.co.metrodata.serverapp.services.countryService;

@RestController
@RequestMapping("/countries")
public class countryControl {
  private countryService countryService;

  public countryControl(id.co.metrodata.serverapp.services.countryService countryService) {
    this.countryService = countryService;
  }

  @GetMapping
  public List<country> getAll() {
    return countryService.getAll();
  }

  @GetMapping("/{id}")
  public country getById(@PathVariable Integer id) {
    return countryService.getById(id);
  }

  @PostMapping
  public country insert(@RequestBody country country) {
    if (country.getRegion().getName().equals(country.getname())) {
      throw new IllegalArgumentException("Region name and country name cannot be the same");
    } else {
      return countryService.insert(country);
    }
  }

  @PutMapping("/{id}")
  public country update(@PathVariable Integer id, @RequestBody country country) {
    return countryService.update(id, country);
  }

  @DeleteMapping("/{id}")
  public country delete(@PathVariable Integer id) {
    return countryService.delete(id);
  }
}