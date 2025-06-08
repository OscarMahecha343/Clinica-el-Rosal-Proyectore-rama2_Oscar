package co.edu.sena.Clinica.el.Rosal.dto;

import co.edu.sena.Clinica.el.Rosal.Entity.ConsultorioEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.EspecialidadEntity;
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
    private EspecialidadEntity especialidad;
    private String correo;
    private String direccion;
    private ConsultorioEntity consultorio;
    private String nombreEspecialidad;
}