package co.edu.sena.Clinica.el.Rosal.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "consultorio") // Mapea esta clase con la tabla "consultorio"
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultorioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre") 
    private String nombreConsultorio;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "capacidad") 
    private String capacidad;

    @Column(name = "telefono") 
    private String telefono;

    @Column(name = "especialidad") 
    private String especialidad;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    public enum Estado {
        ACTIVO, INACTIVO
    }
}
