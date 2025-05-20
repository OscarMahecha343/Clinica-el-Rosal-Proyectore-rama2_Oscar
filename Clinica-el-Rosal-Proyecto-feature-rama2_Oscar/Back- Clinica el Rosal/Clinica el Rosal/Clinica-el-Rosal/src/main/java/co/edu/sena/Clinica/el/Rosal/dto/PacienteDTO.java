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

public class PacienteDTO {
    private Long id;
    private String nombrePaci;
    private String apellidoPaci;
    private String genero;
    private Date fechaNacimiento;
    private String tipoIdentificacion;
    private String identificacion;
    private Long idSeguro;
    private String telefono;
    private String correo;
    private String direccion;
    private String grupoSangineo;
    private String alergias;
    private String tipoAlergia;
    private Long idMunicipio;
}