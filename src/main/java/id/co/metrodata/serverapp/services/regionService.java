package id.co.metrodata.serverapp.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import id.co.metrodata.serverapp.models.region;
import id.co.metrodata.serverapp.repositories.regionRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class regionService {
  private regionRepo regionRepo;

  public List<region> getAll() {
    return regionRepo.findAll();
  }

  public region getById(Integer id) {
    return regionRepo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region not exist!"));
  }

  public region insert(region region) {
    if (regionRepo.existsByName(region.getName())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Region name already exist!");
    }
    return regionRepo.save(region);
  }

  public region update(Integer id, region region) {
    getById(id);
    region.setId(id);
    return regionRepo.save(region);
  }

  public region delete(Integer id) {
    region region = getById(id);
    regionRepo.delete(region);
    return region;
  }

  // JPQL
  public List<region> searchByName(String name) {
    return regionRepo.searchByName(name);
  }

  // Native
  public List<region> searchByNameNative(String name) {
    return regionRepo.searchByNameNative(name);
  }
}
