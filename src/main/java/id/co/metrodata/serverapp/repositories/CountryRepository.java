package id.co.metrodata.serverapp.repositories;

import id.co.metrodata.serverapp.models.Country;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
  // Query Method
  public List<Country> findByRegion_Name(String name);

  public Optional<Country> findTopByRegion_Name(String name);
}
