package id.co.metrodata.serverapp.repositories;

import id.co.metrodata.serverapp.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {}
