package id.co.metrodata.serverapp.controllers;

import id.co.metrodata.serverapp.models.User;
import id.co.metrodata.serverapp.models.dto.request.LoginRequest;
import id.co.metrodata.serverapp.models.dto.request.UserRequest;
import id.co.metrodata.serverapp.models.dto.response.LoginResponse;
import id.co.metrodata.serverapp.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
public class AuthController {

  private AuthService authService;

  @PostMapping("/register")
  public User register(@RequestBody UserRequest userRequest) {
    return authService.register(userRequest);
  }

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest loginRequest) {
    return authService.login(loginRequest);
  }
}
