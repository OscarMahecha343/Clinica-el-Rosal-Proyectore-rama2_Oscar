package co.edu.sena.Clinica.el.Rosal.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleExamenDTO {

    private Long id;

    private Long idTipoExamen;
    private String nombreTipoExamen;

    private String fechaExamen;

    private String archivoExamen;
    private MultipartFile archivo;

    private Long idPaciente;
    private String nombrePaciente;

    private Long idAuxiliar;
    private String nombreAuxiliar;

    private String createdAt;

    
}