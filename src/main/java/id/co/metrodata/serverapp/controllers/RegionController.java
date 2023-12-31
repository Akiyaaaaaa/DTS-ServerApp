package id.co.metrodata.serverapp.controllers;

import id.co.metrodata.serverapp.models.Region;
import id.co.metrodata.serverapp.services.RegionService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/region")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class RegionController {

  private RegionService regionService;

  @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
  @GetMapping
  public List<Region> getAll() {
    return regionService.getAll();
  }

  @PreAuthorize("hasAuthority('READ_ADMIN')")
  @GetMapping("/{id}")
  public Region getById(@PathVariable Integer id) {
    return regionService.getById(id);
  }

  @PreAuthorize("hasAuthority('CREATE_ADMIN')")
  @PostMapping
  public Region create(@RequestBody Region region) {
    return regionService.create(region);
  }

  @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
  @PutMapping("/{id}")
  public Region update(@PathVariable Integer id, @RequestBody Region region) {
    return regionService.update(id, region);
  }

  @PreAuthorize("hasAuthority('DELETE_ADMIN')")
  @DeleteMapping("/{id}")
  public Region delete(@PathVariable Integer id) {
    return regionService.delete(id);
  }

  // JPQL
  @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
  @GetMapping("/jpql/{name}")
  public List<Region> searchByName(@PathVariable String name) {
    return regionService.searchByName(name);
  }

  // Native
  @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
  @GetMapping("/native")
  public List<Region> searchByNameNative(
    @RequestParam(name = "name") String name
  ) {
    return regionService.searchByNameNative(name);
  }
}
// http://localhost:9000/region/id  = path
// http://localhost:9000/region?id=1 = param
