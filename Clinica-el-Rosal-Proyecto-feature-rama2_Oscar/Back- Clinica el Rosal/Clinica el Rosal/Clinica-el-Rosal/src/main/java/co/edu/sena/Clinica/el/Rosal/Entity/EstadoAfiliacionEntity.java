package co.edu.sena.Clinica.el.Rosal.Entity;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado_afiliacion") 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoAfiliacionEntity {

@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_afiliacion", referencedColumnName = "id")
    private PacienteEntity afiliacion;

    @Column(name = "estado")
    private String estadoAfiliacion;

    @Column(name = "fecha_activacion")
    private Date fechaActivacion;

    @Column(name = "fecha_certificado")
    private Date fechaCertificado;

    @Column(name = "observaciones")
    private String observaciones;

}
