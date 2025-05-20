package co.edu.sena.Clinica.el.Rosal.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Esta clase representa la tabla "especialidad" en la base de datos.
@Entity
@Table(name = "especialidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EspecialidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_especialidad", nullable = false, length = 100) // Nombre de la especialidad médica
    private String nombreEspecialidad;
}

