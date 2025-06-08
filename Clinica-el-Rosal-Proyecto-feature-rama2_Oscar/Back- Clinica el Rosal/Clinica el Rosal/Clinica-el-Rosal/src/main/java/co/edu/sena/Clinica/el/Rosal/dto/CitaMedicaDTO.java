package co.edu.sena.Clinica.el.Rosal.dto;

import java.sql.Date;

import co.edu.sena.Clinica.el.Rosal.Entity.CitaMedicaEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitaMedicaDTO {
    private Long id;
    private Long idPaciente;
    private Long idMedico;
    private Long idEspecialidad;
    private Date fecha;
    private String hora;
    private String nombreEspecialidad;
    private String nombreMedico;
    private String consultorio;
    private String ubicacionConsultorio;

    private CitaMedicaEntity.Estado estado;

}
