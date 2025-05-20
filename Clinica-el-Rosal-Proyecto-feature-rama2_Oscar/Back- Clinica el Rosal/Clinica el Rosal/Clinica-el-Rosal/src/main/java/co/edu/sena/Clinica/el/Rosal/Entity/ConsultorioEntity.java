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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre") // Nombre del consultorio
    private String nombreConsultorio;

    @Column(name = "ubicacion") // Ubicación del consultorio (puede ser ID de sede o código)
    private String ubicacion;

    @Column(name = "capacidad") // Capacidad del consultorio (cuántas personas puede atender)
    private String capacidad;

    @Column(name = "telefono") // Teléfono de contacto del consultorio
    private String telefono;

    @Column(name = "especialidad") // Especialidad médica que se atiende en el consultorio
    private String especialidad;

    @Column(name = "estado") // Estado del consultorio: Activo/Inactivo
    private String estado;
}
