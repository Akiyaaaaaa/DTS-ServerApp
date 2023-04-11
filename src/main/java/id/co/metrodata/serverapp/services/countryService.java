package id.co.metrodata.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverapp.models.country;
import id.co.metrodata.serverapp.repositories.countryRepo;

@Service
public class countryService {
  private countryRepo countryRepo;

  public countryService(id.co.metrodata.serverapp.repositories.countryRepo countryRepo) {
    this.countryRepo = countryRepo;
  }

  public List<country> getAll() {
    return countryRepo.findAll();
  }

  public country getById(Integer id) {
    return countryRepo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not exist!"));
  }

  public country insert(country country) {
    return countryRepo.save(country);
  }

  public country update(Integer id, country country) {
    getById(id);
    country.setid(id);
    return countryRepo.save(country);
  }

  public country delete(Integer id) {
    country country = getById(id);
    countryRepo.delete(country);
    return country;
  }
}
