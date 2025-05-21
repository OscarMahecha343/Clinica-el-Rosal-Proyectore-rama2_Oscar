package co.edu.sena.Clinica.el.Rosal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.edu.sena.Clinica.el.Rosal.Entity.AfiliacionEntity;

// Repositorio que extiende de JpaRepository para acceso a base de datos

@Repository
public interface AfiliacionRepository extends 
    JpaRepository<AfiliacionEntity, Long>,   // Provee todos los métodos CRUD básicos
    JpaSpecificationExecutor<AfiliacionEntity> {

     // Permite operaciones CRUD y filtrado avanzado
     // Hereda métodos como save(), findById(), findAll(), deleteById()
}
