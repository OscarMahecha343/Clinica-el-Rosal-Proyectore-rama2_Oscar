package co.edu.sena.Clinica.el.Rosal.Entity;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado_afiliacion") // Tabla asociada en la base de datos
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoAfiliacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Clave primaria auto-incremental
    private Long id;

    @Column(name = "id_afiliacion") // ID de la afiliación relacionada
    private String idAfiliacion; 

    @Column(name = "estado") // Estado de la afiliación (activo, inactivo, suspendido, etc.)
    private String estadoAfiliacion;

    @Column(name = "fecha_activacion") // Fecha de activación de la afiliación
    private Date fechaActivacion;

    @Column(name = "fecha_certificado") // Fecha de expedición del certificado
    private Date fechaCertificado;

    @Column(name = "observaciones") // Observaciones adicionales
    private String observaciones;
}
