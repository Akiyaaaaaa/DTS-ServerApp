package id.co.metrodata.serverapp.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import id.co.metrodata.serverapp.models.employee;
import id.co.metrodata.serverapp.services.employeeService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/employe")
public class employeeControl {

  private employeeService employeeService;

  @GetMapping
  public List<employee> getAll() {
    return employeeService.getAll();
  }

  @GetMapping("/{id}")
  public employee getById(@PathVariable Integer id) {
    return employeeService.getById(id);
  }

  @PostMapping
  public employee insert(@RequestBody employee employee) {
    return employeeService.insert(employee);
  }

  @PutMapping("/{id}")
  public employee update(@PathVariable("id") Integer id, @RequestBody employee employee) {
    return employeeService.update(id, employee);
  }

  @DeleteMapping("/{id}")
  public employee delete(@PathVariable Integer id) {
    return employeeService.delete(id);
  }
}
