package co.edu.sena.Clinica.el.Rosal.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultorioDTO {

    private Long id;
    private String nombreConsultorio;
    private String ubicacion;
    private String capacidad;
    private String telefono;
    private String especialidad;
    private String estado;
}



