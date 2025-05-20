package co.edu.sena.Clinica.el.Rosal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AgendamientoDTO {
    
    private Long id;

    private String fechaAgendamiento;

    private String   horaAgendamiento;

    private String idPaciente;

    private String idMedico;

    private String idEspecialidad;

    private String sede;

    private String estadoDisponibilidad;

    private String motivoConsulta;

    private String idUsuarioCreador;

    private String tipoCreador;

}