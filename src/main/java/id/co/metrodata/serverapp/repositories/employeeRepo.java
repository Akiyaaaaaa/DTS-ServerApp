package id.co.metrodata.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverapp.models.employee;

@Repository
public interface employeeRepo extends JpaRepository<employee, Integer> {
  public Boolean existsByName(String name);
}