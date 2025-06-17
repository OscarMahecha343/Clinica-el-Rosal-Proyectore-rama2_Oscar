package co.edu.sena.Clinica.el.Rosal.Repository;

import java.util.List;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import co.edu.sena.Clinica.el.Rosal.Entity.CitaMedicaEntity;


@Repository
public interface CitaMedicaRepository extends 
        JpaRepository<CitaMedicaEntity, Long>,
        JpaSpecificationExecutor<CitaMedicaEntity> { 
                
                List<CitaMedicaEntity> findByPaciente_Id(Long idPaciente);
                List<CitaMedicaEntity> findByFecha(LocalDate fecha);
                List<CitaMedicaEntity> findByMedico_IdAndFecha(Long idMedico, LocalDate fecha);

}

