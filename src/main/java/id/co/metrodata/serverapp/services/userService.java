package id.co.metrodata.serverapp.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverapp.models.employee;
import id.co.metrodata.serverapp.models.role;
import id.co.metrodata.serverapp.models.user;
import id.co.metrodata.serverapp.models.Dto.request.userReq;
import id.co.metrodata.serverapp.repositories.userRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class userService {
  private userRepo userRepo;
  private ModelMapper modelMapper;
  private roleService roleService;

  public List<user> getAll() {
    return userRepo.findAll();
  }

  public user getById(Integer id) {
    return userRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND!!!"));
  }

  public user insert(userReq userReq) {
    user user = modelMapper.map(userReq, user.class);
    employee employee = modelMapper.map(userReq, employee.class);

    employee.setUser(user);
    user.setEmployee(employee);

    // set default role
    List<role> roles = new ArrayList<>();
    roles.add(roleService.getById(1));
    user.setRoles(roles);

    return userRepo.save(user);
  }

  public user update(Integer id, user user) {
    getById(id);
    user.setId(id);
    return userRepo.save(user);
  }

  public user delete(Integer id) {
    user user = getById(id);
    userRepo.delete(user);
    return user;
  }

  public user addRole(Integer id, role role) {
    user user = getById(id);
    List<role> roles = user.getRoles();
    roles.add(roleService.getById(role.getId()));
    user.setRoles(roles);
    return userRepo.save(user);
  }
}