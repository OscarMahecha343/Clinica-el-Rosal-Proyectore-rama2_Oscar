package co.edu.sena.Clinica.el.Rosal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.edu.sena.Clinica.el.Rosal.Entity.EstadoAfiliacionEntity;

@Repository
public interface EstadoAfiliacionRepository extends 
        JpaRepository<EstadoAfiliacionEntity, Long>,
        JpaSpecificationExecutor<EstadoAfiliacionEntity> {
    // CRUD ya incluido por JpaRepository
}
