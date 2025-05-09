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
@Table(name = "cita")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaMedicaEntity { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Llave primaria autogenerada
    private Long id;

    @Column(name = "id_paciente") // ID del paciente relacionado con la cita
    private Long idPaciente;

    @Column(name = "id_medico") // ID del médico que atenderá la cita
    private Long idMedico;

    @Column(name = "fecha") // Fecha en que se agendó la cita
    private Date fecha; 
    
    @Column(name = "Hora") // Hora en que se realizará la cita
    private String hora;

    @Column(name = "Estado") // Estado actual de la cita (pendiente, realizada, cancelada, etc.)
    private String estado;

    @Column(name = "id_espcialidad") // Especialidad médica correspondiente
    private String idEspecialidad;
}