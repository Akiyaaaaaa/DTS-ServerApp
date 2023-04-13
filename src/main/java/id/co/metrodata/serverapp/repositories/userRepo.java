package id.co.metrodata.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverapp.models.user;

@Repository
public interface userRepo extends JpaRepository<user, Integer> {
  public Boolean existsByName(String name);
}
