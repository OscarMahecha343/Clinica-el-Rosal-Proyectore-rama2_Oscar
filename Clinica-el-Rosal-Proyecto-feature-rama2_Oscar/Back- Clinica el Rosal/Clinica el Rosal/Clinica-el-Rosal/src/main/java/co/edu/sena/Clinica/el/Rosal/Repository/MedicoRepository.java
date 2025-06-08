package co.edu.sena.Clinica.el.Rosal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import co.edu.sena.Clinica.el.Rosal.Entity.MedicoEntity;
import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoEntity, Long>, 
JpaSpecificationExecutor<MedicoEntity> {

 List<MedicoEntity> findByIdEspecialidad_Id(Long idEspecialidad);

}
