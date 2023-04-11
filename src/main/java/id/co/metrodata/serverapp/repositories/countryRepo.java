package id.co.metrodata.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.metrodata.serverapp.models.country;

@Repository
public interface countryRepo extends JpaRepository<country, Integer> {

}
