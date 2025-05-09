package co.edu.sena.Clinica.el.Rosal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.edu.sena.Clinica.el.Rosal.Entity.AuxiliarEntity;

// Repositorio que extiende de JpaRepository para acceso a base de datos
@Repository
public interface AuxiliarRepository extends 
JpaRepository<AuxiliarEntity, Long>,
JpaSpecificationExecutor<AuxiliarEntity> { 
}