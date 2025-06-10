package co.edu.sena.Clinica.el.Rosal.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleExamenDTO {

    private Long id;
    private Long idTipoExamen;
    private String fechaExamen;
    private String archivoExamen;
    private Long idPaciente;
    private Long idAuxiliar;
    private String createdAt;

    private String nombreExamen;
}