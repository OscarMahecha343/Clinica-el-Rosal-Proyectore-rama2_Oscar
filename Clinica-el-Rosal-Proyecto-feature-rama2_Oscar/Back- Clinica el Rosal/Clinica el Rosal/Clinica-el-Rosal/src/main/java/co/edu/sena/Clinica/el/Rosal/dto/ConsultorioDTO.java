package co.edu.sena.Clinica.el.Rosal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultorioDTO { 

    private Long id;
    private String nombreConsultorio;
    private Long ubicacion;
    private String capacidad;
    private String telefono;
    private String especialidad;
    private String estado;
}