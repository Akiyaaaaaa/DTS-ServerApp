package id.co.metrodata.serverapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.metrodata.serverapp.models.user;
import id.co.metrodata.serverapp.models.Dto.request.loginReq;
import id.co.metrodata.serverapp.models.Dto.request.userReq;
import id.co.metrodata.serverapp.models.Dto.response.loginResponse;
import id.co.metrodata.serverapp.services.authService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class authController {
  private authService authService;

  @PostMapping("/register")
  public user register(@RequestBody userReq userReq) {
    return authService.register(userReq);
  }

  @PostMapping("/login")
  public loginResponse login(@RequestBody loginReq loginReq) {
    return authService.login(loginReq);
  }
}
