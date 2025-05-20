package co.edu.sena.Clinica.el.Rosal.Entity;


import java.sql.Date;

import jakarta.persistence.Column;
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
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "cantidad")  
    private Integer cantidad;

    @Column(name = "descripcion")  
    private String descripcion;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "unidad_medida")
    private String unidadMedida;

    @Column(name = "precio_unitario")
    private int precioUnitario;

    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;

    @Column(name = "proveedor")
    private String proveedor;

    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;

    @Column(name = "estado")
    private String estado;
}


