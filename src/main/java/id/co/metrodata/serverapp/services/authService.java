package id.co.metrodata.serverapp.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.co.metrodata.serverapp.models.employee;
import id.co.metrodata.serverapp.models.role;
import id.co.metrodata.serverapp.models.user;
import id.co.metrodata.serverapp.models.Dto.request.userReq;
import id.co.metrodata.serverapp.repositories.userRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class authService {
  private ModelMapper modelMapper;
  private roleService roleService;
  private userRepo userRepo;
  private PasswordEncoder passwordEncoder;

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
}
