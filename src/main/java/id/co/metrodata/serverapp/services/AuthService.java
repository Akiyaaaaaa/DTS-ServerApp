package id.co.metrodata.serverapp.services;

import id.co.metrodata.serverapp.models.Employee;
import id.co.metrodata.serverapp.models.Role;
import id.co.metrodata.serverapp.models.User;
import id.co.metrodata.serverapp.models.dto.request.UserRequest;
import id.co.metrodata.serverapp.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

  private ModelMapper modelMapper;
  private RoleService roleService;
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

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
}
