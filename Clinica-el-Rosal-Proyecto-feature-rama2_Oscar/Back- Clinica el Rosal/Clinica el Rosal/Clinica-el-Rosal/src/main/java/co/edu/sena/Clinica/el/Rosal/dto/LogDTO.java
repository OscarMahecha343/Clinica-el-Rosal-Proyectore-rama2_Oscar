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
public class LogDTO {

    private Long id;

    private String referencia;
   
    private String data;

    private Date fecha;

    private Long idUsuario;

    private String estado;   

}
