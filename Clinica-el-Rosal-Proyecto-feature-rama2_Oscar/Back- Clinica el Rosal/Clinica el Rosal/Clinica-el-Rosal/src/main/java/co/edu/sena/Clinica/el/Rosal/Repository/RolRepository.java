package co.edu.sena.Clinica.el.Rosal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import co.edu.sena.Clinica.el.Rosal.Entity.RolEntity;

public interface RolRepository extends JpaRepository<RolEntity, Long>,
JpaSpecificationExecutor<RolEntity>{
}