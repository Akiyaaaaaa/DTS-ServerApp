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
import id.co.metrodata.serverapp.models.region;
import id.co.metrodata.serverapp.services.regionService;

@RestController
@RequestMapping("/region")
public class regionControl {
  private regionService regionService;

  public regionControl(id.co.metrodata.serverapp.services.regionService regionService) {
    this.regionService = regionService;
  }

  @GetMapping
  public List<region> getAll() {
    return regionService.getAll();
  }

  @GetMapping("/{id}")
  public region getById(@PathVariable Integer id) {
    return regionService.getById(id);
  }

  @PostMapping
  public region insert(@RequestBody region region) {
    return regionService.insert(region);
  }

  @PutMapping("/{id}")
  public region update(@PathVariable("id") Integer id, @RequestBody region region) {
    return regionService.update(id, region);
  }

  @DeleteMapping("/{id}")
  public region delete(@PathVariable Integer id) {
    return regionService.delete(id);
  }
}