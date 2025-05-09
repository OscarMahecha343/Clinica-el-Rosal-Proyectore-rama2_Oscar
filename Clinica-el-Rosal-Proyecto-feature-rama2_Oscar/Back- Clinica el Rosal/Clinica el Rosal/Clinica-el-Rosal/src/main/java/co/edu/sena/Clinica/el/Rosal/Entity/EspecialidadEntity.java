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

@Entity
@Table(name = "especialidad") // Nombre de la tabla en la base de datos
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EspecialidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Llave primaria autogenerada
    private Long id;

    @Column(name = "nombre_especialidad") // Nombre de la especialidad médica
    private String nombreEspecialidad;
}
