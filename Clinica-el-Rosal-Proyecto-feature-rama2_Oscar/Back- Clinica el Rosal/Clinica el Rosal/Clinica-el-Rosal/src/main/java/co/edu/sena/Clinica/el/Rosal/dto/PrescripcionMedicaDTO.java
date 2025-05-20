package co.edu.sena.Clinica.el.Rosal.dto;

import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrescripcionMedicaDTO {

    private Long id;
    private Long idHistoria;
    private Long idMedicamentos;
    private int cantidadTotal;
    private String presentacion;
    private String indicaciones;
}
