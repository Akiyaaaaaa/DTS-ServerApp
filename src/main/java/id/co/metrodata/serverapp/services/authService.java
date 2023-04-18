package id.co.metrodata.serverapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.co.metrodata.serverapp.models.employee;
import id.co.metrodata.serverapp.models.role;
import id.co.metrodata.serverapp.models.user;
import id.co.metrodata.serverapp.models.Dto.request.loginReq;
import id.co.metrodata.serverapp.models.Dto.request.userReq;
import id.co.metrodata.serverapp.models.Dto.response.loginResponse;
import id.co.metrodata.serverapp.repositories.userRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class authService {
  private ModelMapper modelMapper;
  private roleService roleService;
  private userRepo userRepo;
  private PasswordEncoder passwordEncoder;
  private AuthenticationManager authenticationManager;
  private userDetailService userDetailService;

  public user register(userReq userReq) {
    user user = modelMapper.map(userReq, user.class);
    employee employee = modelMapper.map(userReq, employee.class);

    employee.setUser(user);
    user.setEmployee(employee);

    // set defult role
    List<role> roles = new ArrayList<>();
    roles.add(roleService.getById(1));
    user.setRoles(roles);

    // set password
    user.setPassword(passwordEncoder.encode(userReq.getPassword()));

    return userRepo.save(user);
  }

  // login
  public loginResponse login(loginReq loginReq) {
    // authentication => login req
    UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(loginReq.getUsername(),
        loginReq.getPassword());

    // set principle
    Authentication auth = authenticationManager.authenticate(authReq);
    SecurityContextHolder.getContext().setAuthentication(auth);

    user user = userRepo.findByUsernameOrEmployeeEmail(loginReq.getUsername(), loginReq.getPassword()).get();

    UserDetails userDetails = userDetailService.loadUserByUsername(loginReq.getUsername());

    List<String> authorities = userDetails.getAuthorities().stream().map(authority -> authority.getAuthority())
        .collect(Collectors.toList());

    // user detail => login response
    return new loginResponse(user.getUsername(), user.getEmployee().getEmail(), authorities);
  }
}
