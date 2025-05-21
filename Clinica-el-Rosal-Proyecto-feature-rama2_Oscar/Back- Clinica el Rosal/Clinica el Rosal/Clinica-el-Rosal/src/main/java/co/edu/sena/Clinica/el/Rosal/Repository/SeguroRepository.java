package co.edu.sena.Clinica.el.Rosal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.sena.Clinica.el.Rosal.Entity.SeguroEntity;

@Repository
public interface SeguroRepository extends JpaRepository<SeguroEntity, Long> {
}
