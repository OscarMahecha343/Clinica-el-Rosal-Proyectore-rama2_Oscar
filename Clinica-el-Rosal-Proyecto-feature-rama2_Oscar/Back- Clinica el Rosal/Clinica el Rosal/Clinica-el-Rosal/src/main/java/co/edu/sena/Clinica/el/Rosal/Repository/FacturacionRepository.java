package co.edu.sena.Clinica.el.Rosal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.edu.sena.Clinica.el.Rosal.Entity.FacturacionEntity;

@Repository
public interface FacturacionRepository extends 
        JpaRepository<FacturacionEntity, Long>, 
        JpaSpecificationExecutor<FacturacionEntity> {
    // Métodos CRUD ya implementados por JpaRepository
}
