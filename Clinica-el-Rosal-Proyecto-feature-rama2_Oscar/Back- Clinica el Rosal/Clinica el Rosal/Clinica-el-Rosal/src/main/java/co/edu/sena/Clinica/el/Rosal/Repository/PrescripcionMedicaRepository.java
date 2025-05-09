package co.edu.sena.Clinica.el.Rosal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.sena.Clinica.el.Rosal.Entity.PrescripcionMedicaEntity;

public interface PrescripcionMedicaRepository extends JpaRepository<PrescripcionMedicaEntity, Long> {
}