package co.edu.sena.Clinica.el.Rosal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MedicoDTO {
    private Long id;
    private String nombreMedico;
    private String apellidosMedicos;
    private String telefonoDoc;
    private String licenciaMedica;
    private Long idEspecialidad;
    private String correo;
    private String direccion;
    private Long consultorio;
}