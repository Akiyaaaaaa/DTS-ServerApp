package id.co.metrodata.serverapp.services;

import id.co.metrodata.serverapp.models.Employee;
import id.co.metrodata.serverapp.models.Role;
import id.co.metrodata.serverapp.models.User;
import id.co.metrodata.serverapp.models.dto.request.LoginRequest;
import id.co.metrodata.serverapp.models.dto.request.UserRequest;
import id.co.metrodata.serverapp.models.dto.response.LoginResponse;
import id.co.metrodata.serverapp.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

  private ModelMapper modelMapper;
  private RoleService roleService;
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private AuthenticationManager authenticationManager;
  private AppUserDetailService appUserDetailService;

  public User register(UserRequest userRequest) {
    User user = modelMapper.map(userRequest, User.class);
    Employee employee = modelMapper.map(userRequest, Employee.class);

    employee.setUser(user);
    user.setEmployee(employee);

    // set default role
    List<Role> roles = new ArrayList<>();
    roles.add(roleService.getById(1));
    user.setRoles(roles);

    // set password
    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

    return userRepository.save(user);
  }

  public LoginResponse login(LoginRequest loginRequest) {
    // authentication => login request
    UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
      loginRequest.getUsername(),
      loginRequest.getPassword()
    );

    // set principle
    Authentication auth = authenticationManager.authenticate(authReq);
    SecurityContextHolder.getContext().setAuthentication(auth);

    User user = userRepository
      .findByUsernameOrEmployee_Email(
        loginRequest.getUsername(),
        loginRequest.getUsername()
      )
      .get();

    UserDetails userDetails = appUserDetailService.loadUserByUsername(
      loginRequest.getUsername()
    );

    List<String> authorities = userDetails
      .getAuthorities()
      .stream()
      .map(authority -> authority.getAuthority())
      .collect(Collectors.toList());

    // user detail => login response
    return new LoginResponse(
      user.getUsername(),
      user.getEmployee().getEmail(),
      authorities
    );
  }
}
