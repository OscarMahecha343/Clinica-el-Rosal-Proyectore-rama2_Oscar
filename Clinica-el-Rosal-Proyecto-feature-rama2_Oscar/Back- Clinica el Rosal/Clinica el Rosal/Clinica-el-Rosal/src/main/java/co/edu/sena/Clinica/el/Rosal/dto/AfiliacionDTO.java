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
public class AfiliacionDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String tipoIdentificacion;
    private String identificacion;
    private Date fechaNacimiento;
    private String telefono;
    private String correo;
    private String direccion;
    private String idMunicipio;
    private String tipoAfiliacion;
    private String idSeguro;
}