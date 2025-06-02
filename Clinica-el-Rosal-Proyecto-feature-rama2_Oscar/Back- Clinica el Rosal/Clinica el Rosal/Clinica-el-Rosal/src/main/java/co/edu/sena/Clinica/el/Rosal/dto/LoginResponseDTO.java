package co.edu.sena.Clinica.el.Rosal.dto;

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
    private String tipoIdentificacion;
    private String identificacion;
    private String username;
    private String email;
    private String rol; 
    private boolean isActive;

}
