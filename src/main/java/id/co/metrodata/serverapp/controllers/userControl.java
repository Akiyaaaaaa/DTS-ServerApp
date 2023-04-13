package id.co.metrodata.serverapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.metrodata.serverapp.models.user;
import id.co.metrodata.serverapp.services.userService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class userControl {

  private userService userService;

  @GetMapping
  public List<user> getAll() {
    return userService.getAll();
  }

  @GetMapping("/{id}")
  public user getById(@PathVariable Integer id) {
    return userService.getById(id);
  }

  @PostMapping
  public user insert(@RequestBody user user) {
    return userService.insert(user);
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