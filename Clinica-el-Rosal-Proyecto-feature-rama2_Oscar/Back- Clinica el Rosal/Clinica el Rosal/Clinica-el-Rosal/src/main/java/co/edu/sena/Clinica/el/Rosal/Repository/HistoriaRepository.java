package co.edu.sena.Clinica.el.Rosal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.edu.sena.Clinica.el.Rosal.Entity.HistoriaEntity;

@Repository
public interface HistoriaRepository extends 
JpaRepository<HistoriaEntity, Long>,
JpaSpecificationExecutor<HistoriaEntity> { 
}