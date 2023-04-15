package id.co.metrodata.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverapp.models.employee;
import id.co.metrodata.serverapp.repositories.employeeRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class employeeService {
  private employeeRepo employeeRepo;

  public List<employee> getAll() {
    return employeeRepo.findAll();
  }

  public employee getById(Integer id) {
    return employeeRepo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND!!!"));
  }

  public employee insert(employee employee) {
    return employeeRepo.save(employee);
  }

  public employee update(Integer id, employee employee) {
    getById(id);
    employee.setId(id);
    return employeeRepo.save(employee);
  }

  public employee delete(Integer id) {
    employee employee = getById(id);
    employeeRepo.delete(employee);
    return employee;
  }
}
