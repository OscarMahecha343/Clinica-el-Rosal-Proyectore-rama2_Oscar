package co.edu.sena.Clinica.el.Rosal.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "prescripción medica")
@Data
public class PrescripcionMedicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_historia")
    private Long idHistoria;

    @Column(name = "id_medicamentos")
    private Long idMedicamentos;

    @Column(name = "cantidad_total")
    private int cantidadTotal;

    @Column(name = "presentacion")
    private String presentacion;

    @Column(name = "indicaciones")
    private String indicaciones;
}




