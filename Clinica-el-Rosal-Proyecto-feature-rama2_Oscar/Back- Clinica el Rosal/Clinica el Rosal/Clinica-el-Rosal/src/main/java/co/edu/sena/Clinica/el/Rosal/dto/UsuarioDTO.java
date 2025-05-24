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
public class UsuarioDTO {
    private Long id;
    private String login;
    private String password;
    private Long idPaciente;
    private Long idMedico;
    private Long idAuxiliar;
    private Long idFarmaceutico;
    private Long idRol;
    private String codigoRestablecimiento;
    private Date expiracionCodigo;
    private Date ultimaSolicitud;
    private int intentosFallidos;
}