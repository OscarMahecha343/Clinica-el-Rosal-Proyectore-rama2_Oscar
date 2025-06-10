package co.edu.sena.Clinica.el.Rosal.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import co.edu.sena.Clinica.el.Rosal.Entity.EstadoAfiliacionEntity;

@Repository
public interface EstadoAfiliacionRepository extends 
        JpaRepository<EstadoAfiliacionEntity, Long>, 
        JpaSpecificationExecutor<EstadoAfiliacionEntity> { 
                
                Optional<EstadoAfiliacionEntity> findByAfiliacion_Id(Long id);
}
