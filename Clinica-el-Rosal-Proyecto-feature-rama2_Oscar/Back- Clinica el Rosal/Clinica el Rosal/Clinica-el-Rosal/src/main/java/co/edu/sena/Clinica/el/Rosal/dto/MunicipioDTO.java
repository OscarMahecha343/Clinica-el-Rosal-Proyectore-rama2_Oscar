package co.edu.sena.Clinica.el.Rosal.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MunicipioDTO {

    private Long id;
    private String nombreMunicipio;
    private Long idDepartamento;
    private String estado;
}