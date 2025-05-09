package co.edu.sena.Clinica.el.Rosal.Entity;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "inventario_medicamentos")
@Data
public class InventarioMedicamentosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
   
    private Long id;

    
    private String nombre;

   
    private Integer cantidad;

    
    private String descripcion;

    private String categoria;

    
    private String unidadMedida;

   
    private Double precioUnitario;

   
    private LocalDate fechaVencimiento;

 
    private String proveedor;

    
    private LocalDate fechaActualizacion;

    private String estado;
}