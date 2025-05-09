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
public class HistoriaDTO { 
 
    private Long id;
  
    private Long idPaciente;

    private Long idMedico;

    private Date fechaConsulta;

    private String motivoConsulta;

    private String historialClinico;

    private String diagnostico;

    private String tratamiento;

    private String alergias;

    private String antecedentes;

    private String signosVitales;

    private String examenesSolicitados;

}