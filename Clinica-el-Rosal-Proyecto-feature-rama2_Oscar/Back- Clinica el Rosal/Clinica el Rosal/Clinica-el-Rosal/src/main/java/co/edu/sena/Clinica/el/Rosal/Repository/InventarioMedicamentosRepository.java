package co.edu.sena.Clinica.el.Rosal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.edu.sena.Clinica.el.Rosal.Entity.InventarioMedicamentosEntity;

@Repository
public interface InventarioMedicamentosRepository extends 
    JpaRepository<InventarioMedicamentosEntity, Long>,
    JpaSpecificationExecutor<InventarioMedicamentosEntity> {
}