package co.edu.sena.Clinica.el.Rosal.Entity;

import jakarta.persistence.*;
import lombok.*;

// Marca esta clase como entidad de JPA
@Entity
@Table(name = "detalle_examenes") // Tabla en la base de datos
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleExamenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Clave primaria autoincremental
    private Long id;

    @Column(name = "id_tipo_examen")
    private Long idTipoExamen; // ID de tipo de examen (clave foránea si aplica)

    @Column(name = "fecha_examen")
    private String fechaExamen; // Fecha en que se realizó el examen

    @Column(name = "archivo_examen")
    private String archivoExamen; // Ruta o nombre del archivo PDF o imagen

    @Column(name = "id_paciente")
    private Long idPaciente; // ID del paciente (clave foránea si aplica)

    @Column(name = "id_auxiliar")
    private Long idAuxiliar; // ID del auxiliar que subió el examen

    @Column(name = "created_at")
    private String createdAt; // Fecha y hora de creación del registro
}





