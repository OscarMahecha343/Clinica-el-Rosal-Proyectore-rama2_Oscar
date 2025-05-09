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
@Table(name = "detalle_examenes") // Nombre de la tabla en la BD
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
    private String idTipoExamen;

    @Column(name = "fecha_examen")
    private String fechaExamen;

    @Column(name = "archivo_examen")
    private String archivoExamen;

    @Column(name = "id_paciente")
    private String idPaciente;

    @Column(name = "id_auxiliar")
    private String idAuxiliar;

    @Column(name = "created_at")
    private String createdAt;
}





