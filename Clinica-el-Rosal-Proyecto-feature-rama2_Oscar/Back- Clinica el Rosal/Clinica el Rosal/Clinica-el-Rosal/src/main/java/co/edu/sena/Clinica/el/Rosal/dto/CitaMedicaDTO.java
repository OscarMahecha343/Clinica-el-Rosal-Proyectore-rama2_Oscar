package co.edu.sena.Clinica.el.Rosal.dto;

import java.sql.Date;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitaMedicaDTO {

    private Long id;
    private Long idPaciente;
    private Long idMedico;
    private Date fecha; 
    private String hora;
    private String estado;
    private String idEspecialidad;
}