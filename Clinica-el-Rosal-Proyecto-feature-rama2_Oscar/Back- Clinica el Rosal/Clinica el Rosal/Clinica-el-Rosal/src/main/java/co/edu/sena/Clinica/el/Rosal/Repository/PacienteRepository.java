package co.edu.sena.Clinica.el.Rosal.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.edu.sena.Clinica.el.Rosal.Entity.PacienteEntity;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long>, 
JpaSpecificationExecutor<PacienteEntity> {

    Optional<PacienteEntity> findByIdentificacion(String identificacion);
}
