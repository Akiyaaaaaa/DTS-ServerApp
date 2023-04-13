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

import id.co.metrodata.serverapp.models.role;
import id.co.metrodata.serverapp.services.roleService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/role")
public class roleControl {

  private roleService roleService;

  @GetMapping
  public List<role> getAll() {
    return roleService.getAll();
  }

  @GetMapping("/{id}")
  public role getById(@PathVariable Integer id) {
    return roleService.getById(id);
  }

  @PostMapping
  public role insert(@RequestBody role role) {
    return roleService.insert(role);
  }

  @PutMapping("/{id}")
  public role update(@PathVariable("id") Integer id, @RequestBody role role) {
    return roleService.update(id, role);
  }

  @DeleteMapping("/{id}")
  public role delete(@PathVariable Integer id) {
    return roleService.delete(id);
  }

  // // JPQL
  // @GetMapping("/jpql/{name}")
  // public List<region> searchByName(@PathVariable String name) {
  // return regionService.searchByName(name);
  // }

  // // Native
  // @GetMapping("/native")
  // public List<region> searchByNameNative(
  // @RequestParam(name = "name") String name) {
  // return regionService.searchByNameNative(name);
  // }
}
