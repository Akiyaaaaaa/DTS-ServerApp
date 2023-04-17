package id.co.metrodata.serverapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverapp.models.user;

@Repository
public interface userRepo extends JpaRepository<user, Integer> {

  Optional<user> findByUsernameOrEmployeeEmail(String username, String email);
  // @Query("SELECT u FROM user u WHERE u.username = :username")
  // public user getUserByUserName(@Param("username") String username);

}