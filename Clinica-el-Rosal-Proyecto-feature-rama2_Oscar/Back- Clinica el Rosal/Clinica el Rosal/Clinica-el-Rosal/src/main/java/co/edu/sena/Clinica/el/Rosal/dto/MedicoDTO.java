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

    private Double telefonoDoc;

    private Double licenciaMedica;

    private String idEspecialidad;

    private String correo;

    private Long direccion;

    private Long consultorio;
}