package id.co.metrodata.serverapp.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import id.co.metrodata.serverapp.models.region;

@Repository
public interface regionRepo extends JpaRepository<region, Integer> {

  // Jpl
  @Query("SELECT r FROM region r WHERE r.name = ?1")
  public List<region> searchByName(String name);

  // Native
  @Query(value = "SELECT * FROM regions WHERE name = :name", nativeQuery = true)
  public List<region> searchByNameNative(String name);

  // Query Method
  public Boolean existsByName(String name);
}
