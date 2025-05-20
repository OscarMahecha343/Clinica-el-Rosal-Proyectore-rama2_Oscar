package co.edu.sena.Clinica.el.Rosal.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartamentoDTO {
    private Long id;
    private String nombreDepartamento; // Nombre que el frontend enviará
}
