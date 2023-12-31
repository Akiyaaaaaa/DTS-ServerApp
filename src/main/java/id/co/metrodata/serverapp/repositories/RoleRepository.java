package id.co.metrodata.serverapp.repositories;

import id.co.metrodata.serverapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {}
