package co.edu.sena.Clinica.el.Rosal.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class LoginResponseDTO {

    private Long id;
  
    private String nombre;
    private String apellido;
    private String genero;
    private Date fechaNacimiento;
    private String tipoIdentificacion;
    private String identificacion;
    private Long idSeguro;
    private String telefono;
    private String direccion;
    private String grupoSanguineo;
    private String alergias;
    private String tipoDeAlergia;
    private Long idMunicipio;
    private String username;
    private String rol;
    private boolean isActive;

}
