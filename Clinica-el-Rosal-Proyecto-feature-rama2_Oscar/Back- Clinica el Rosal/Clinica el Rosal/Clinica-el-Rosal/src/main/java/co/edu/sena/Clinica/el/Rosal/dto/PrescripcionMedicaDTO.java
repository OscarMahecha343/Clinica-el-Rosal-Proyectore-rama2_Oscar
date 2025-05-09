package co.edu.sena.Clinica.el.Rosal.dto;

import lombok.Data;

@Data
public class PrescripcionMedicaDTO {
    private Long id;
    private String medicamento;
    private String dosis;
    private Long pacienteId;
}
