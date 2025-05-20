package co.edu.sena.Clinica.el.Rosal.Entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cita")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaMedicaEntity { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Clave primaria autogenerada
    private Long id;

    @Column(name = "id_paciente") // ID del paciente que asiste a la cita
    private Long idPaciente;

    @Column(name = "id_medico") // ID del médico asignado a la cita
    private Long idMedico;

    @Column(name = "fecha") // Fecha en que se realizará la cita
    private Date fecha; 

    @Column(name = "hora") // Hora exacta de la cita
    private String hora;

    @Column(name = "estado") // Estado de la cita: pendiente, realizada, cancelada, etc.
    private String estado;

    @Column(name = "id_especialidad") // ID o código de la especialidad requerida
    private String idEspecialidad;
}