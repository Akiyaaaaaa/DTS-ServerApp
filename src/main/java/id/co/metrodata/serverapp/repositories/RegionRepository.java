package id.co.metrodata.serverapp.repositories;

import id.co.metrodata.serverapp.models.Region;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
  // JPQL
  @Query("SELECT r FROM Region r WHERE r.name = ?1")
  public List<Region> searchByName(String name);

  // Native
  @Query(
    value = "SELECT * FROM tb_Region WHERE region_name = :name",
    nativeQuery = true
  )
  public List<Region> searchByNameNative(String name);

  // Query Method
  public Boolean existsByName(String name);
}
