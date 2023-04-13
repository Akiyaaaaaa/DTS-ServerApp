package id.co.metrodata.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverapp.models.role;

@Repository
public interface roleRepo extends JpaRepository<role, Integer> {
  public Boolean existsByName(String name);
}
