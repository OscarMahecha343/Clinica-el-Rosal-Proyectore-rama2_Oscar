package co.edu.sena.Clinica.el.Rosal.dto;

import java.time.LocalDate;

import co.edu.sena.Clinica.el.Rosal.Entity.CitaMedicaEntity;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitaMedicaDTO {
    private Long id;
    private Long idPaciente;
    private Long idMedico;
    private Long idEspecialidad;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    private String hora;
    private String nombreEspecialidad;
    private String nombreMedico;
    private String consultorio;
    private String ubicacionConsultorio;

    private CitaMedicaEntity.Estado estado;

}
