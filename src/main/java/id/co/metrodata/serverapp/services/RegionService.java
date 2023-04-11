package id.co.metrodata.serverapp.services;

import id.co.metrodata.serverapp.models.Region;
import id.co.metrodata.serverapp.repositories.RegionRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegionService {

  private RegionRepository regionRepository;

  @Autowired
  public RegionService(RegionRepository regionRepository) {
    this.regionRepository = regionRepository;
  }

  public List<Region> getAll() {
    return regionRepository.findAll();
  }

  public Region getById(Integer id) {
    return regionRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Region not found!!!")
      );
  }

  public Region create(Region region) {
    return regionRepository.save(region);
  }

  public Region update(Integer id, Region region) {
    getById(id);
    region.setId(id);
    return regionRepository.save(region);
  }

  public Region delete(Integer id) {
    Region region = getById(id);
    regionRepository.delete(region);
    return region;
  }
}
