package co.edu.sena.Clinica.el.Rosal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuxiliarDTO { 

    private Long id;

    private String nombreAuxiliar;

    private String apellidoAuxiliar;

    private String identificacion;

    private String telefono;

    private String correo;

    private String direccion;

}