package id.co.metrodata.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.metrodata.serverapp.models.user;

public interface userRepo extends JpaRepository<user, Integer> {

  @Query("SELECT u FROM user u WHERE u.username = :username")
  public user getUserByUserName(@Param("username") String username);
}