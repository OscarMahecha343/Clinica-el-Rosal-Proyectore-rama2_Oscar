package co.edu.sena.Clinica.el.Rosal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.edu.sena.Clinica.el.Rosal.Entity.ConsultorioEntity;

@Repository
public interface ConsultorioRepository extends 
    JpaRepository<ConsultorioEntity, Long>, 
    JpaSpecificationExecutor<ConsultorioEntity> { 
    // JpaRepository incluye métodos como save(), findAll(), deleteById(), etc.
}