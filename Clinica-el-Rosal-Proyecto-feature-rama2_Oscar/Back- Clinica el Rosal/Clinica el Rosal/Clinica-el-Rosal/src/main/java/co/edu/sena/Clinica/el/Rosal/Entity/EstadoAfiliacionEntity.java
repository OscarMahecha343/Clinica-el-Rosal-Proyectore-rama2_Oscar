package co.edu.sena.Clinica.el.Rosal.Entity;

import java.sql.Date;

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

    @Column(name = "id_afiliacion") // ID de la afiliación a la que pertenece el estado
    private String idAfilicion;

    @Column(name = "estado") // Estado actual de la afiliación (ej. activo, suspendido)
    private String estadoAfiliacion;

    @Column(name = "fecha_activacion") // Fecha de activación de la afiliación
    private Date fechaActivacion;

    @Column(name = "fecha_certificado") // Fecha de expedición del certificado
    private Date fechaCertificado;

    @Column(name = "observaciones") // Observaciones adicionales
    private String observaciones;
}
