package co.edu.sena.Clinica.el.Rosal.dto;

import lombok.*;

// Objeto para la transferencia de datos entre backend y frontend
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EspecialidadDTO {
    private Long id;
    private String nombreEspecialidad;
}
