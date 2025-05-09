package co.edu.sena.Clinica.el.Rosal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class FarmaceuticoDTO {
    private Long id;

    private String nombreFarmaceuta;

    private String apellidoFarmaceuta;

    private Double numeroLicencia;

    private Double telefonoFarmaceuta;

    private String correoFarmaceuta;

    private String dirreccionFarmaceuta;
}