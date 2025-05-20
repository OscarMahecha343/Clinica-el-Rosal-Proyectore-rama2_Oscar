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
public class InventarioMedicamentosDTO {

    private Long id;
    private String nombre;
    private Integer cantidad;
    private String descripcion;
    private String categoria;
    private String unidadMedida;
    private int precioUnitario;
    private Date fechaVencimiento;
    private String proveedor;
    private Date fechaActualizacion;
    private String estado;
}