package co.edu.sena.Clinica.el.Rosal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import co.edu.sena.Clinica.el.Rosal.Entity.DetalleExamenEntity;

@Repository
public interface DetalleExamenRepository extends
        JpaRepository<DetalleExamenEntity, Long>,
        JpaSpecificationExecutor<DetalleExamenEntity> {

                List<DetalleExamenEntity> findByIdPaciente(Long idPaciente);
}