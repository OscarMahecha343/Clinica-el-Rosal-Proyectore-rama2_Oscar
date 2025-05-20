package co.edu.sena.Clinica.el.Rosal.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacturacionDTO {

    private Long id;
    private Long idPaciente;
    private Long idServicio;
    private Double monto;
    private Date fecha;
}
