package id.co.metrodata.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.metrodata.serverapp.models.user;

public interface userRepo extends JpaRepository<user, Integer> {
  public Boolean existsByName(String name);
}