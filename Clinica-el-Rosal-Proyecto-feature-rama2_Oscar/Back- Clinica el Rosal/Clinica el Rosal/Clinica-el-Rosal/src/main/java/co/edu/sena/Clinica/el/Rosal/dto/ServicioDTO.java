package co.edu.sena.Clinica.el.Rosal.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicioDTO {
    private Long id;
    private String descripcionServicio;
    private String tipoServicio;
}
