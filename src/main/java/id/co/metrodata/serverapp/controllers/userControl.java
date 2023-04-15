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
import id.co.metrodata.serverapp.models.user;
import id.co.metrodata.serverapp.models.Dto.request.userReq;
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
  public user insert(@RequestBody userReq userReq) {
    return userService.insert(userReq);
  }

  @PutMapping("/{id}")
  public user update(@PathVariable Integer id, @RequestBody user user) {
    return userService.update(id, user);
  }

  @DeleteMapping("/{id}")
  public user delete(@PathVariable Integer id) {
    return userService.delete(id);
  }

  @PostMapping("/{id}")
  public user addRole(@PathVariable Integer id, @RequestBody role role) {
    return userService.addRole(id, role);
  }
}