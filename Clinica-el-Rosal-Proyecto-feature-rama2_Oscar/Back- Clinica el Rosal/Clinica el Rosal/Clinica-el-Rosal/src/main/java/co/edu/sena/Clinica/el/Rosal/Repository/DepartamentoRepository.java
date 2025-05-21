package co.edu.sena.Clinica.el.Rosal.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import co.edu.sena.Clinica.el.Rosal.Entity.DepartamentoEntity;

@Repository
public interface DepartamentoRepository extends 
        JpaRepository<DepartamentoEntity, Long>, 
        JpaSpecificationExecutor<DepartamentoEntity> {
    
}