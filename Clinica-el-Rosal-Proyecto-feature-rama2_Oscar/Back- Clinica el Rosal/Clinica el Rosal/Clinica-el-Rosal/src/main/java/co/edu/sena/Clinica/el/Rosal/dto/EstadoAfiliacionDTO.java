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
public class EstadoAfiliacionDTO {

    private Long id;
    private Long idAfiliacion; 
    private String nombreAfiliado; 
    private String apellidoAfiliado; 
    private String estadoAfiliacion;
    private Date fechaActivacion;
    private Date fechaCertificado;
    private String observaciones;
}
