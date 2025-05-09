package co.edu.sena.Clinica.el.Rosal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleExamenDTO {

    private Long id;
    private String idTipoExamen;
    private String fechaExamen;
    private String archivoExamen;
    private String idPaciente;
    private String idAuxiliar;
    private String createdAt;
}