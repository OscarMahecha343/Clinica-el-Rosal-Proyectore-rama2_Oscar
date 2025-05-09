package co.edu.sena.Clinica.el.Rosal.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String username;
    private String password;
    private String correo;
}
