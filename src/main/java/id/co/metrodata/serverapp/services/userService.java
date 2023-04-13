package id.co.metrodata.serverapp.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverapp.models.user;
import id.co.metrodata.serverapp.repositories.userRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class userService {
  private userRepo userRepo;

  public List<user> getAll() {
    return userRepo.findAll();
  }

  public user getById(Integer id) {
    return userRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND!!!"));
  }

  public user insert(user user) {
    if (userRepo.existsByName(user.getName())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Name already Exist!!");
    }
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

}