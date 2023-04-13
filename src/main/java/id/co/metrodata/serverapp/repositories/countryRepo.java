package id.co.metrodata.serverapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.co.metrodata.serverapp.models.country;

@Repository
public interface countryRepo extends JpaRepository<country, Integer> {

  public List<country> findByRegionName(String name);

  public Optional<country> findTopByRegionName(String name);
}
