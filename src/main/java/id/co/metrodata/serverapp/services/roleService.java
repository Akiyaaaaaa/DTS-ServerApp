package id.co.metrodata.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverapp.models.role;
import id.co.metrodata.serverapp.repositories.roleRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class roleService {
  private roleRepo roleRepo;

  public List<role> getAll() {
    return roleRepo.findAll();

  }

  public role getById(Integer id) {
    return roleRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND!!!"));
  }

  public role insert(role role) {
    return roleRepo.save(role);
  }

  public role update(Integer id, role role) {
    getById(id);
    role.setId(id);
    return roleRepo.save(role);
  }

  public role delete(Integer id) {
    role role = getById(id);
    roleRepo.delete(role);
    return role;
  }

}
