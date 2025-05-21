package co.edu.sena.Clinica.el.Rosal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.edu.sena.Clinica.el.Rosal.Entity.FarmaceuticoEntity;

@Repository
public interface FarmaceuticoRepository extends 
        JpaRepository<FarmaceuticoEntity, Long>,
        JpaSpecificationExecutor<FarmaceuticoEntity> {
    
}