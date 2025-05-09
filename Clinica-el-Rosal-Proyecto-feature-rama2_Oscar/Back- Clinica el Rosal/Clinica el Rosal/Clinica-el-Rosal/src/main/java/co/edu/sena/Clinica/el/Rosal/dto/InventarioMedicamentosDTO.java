package co.edu.sena.Clinica.el.Rosal.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class InventarioMedicamentosDTO {
    private Long id;
    private String nombre;
    private Integer cantidad;
    private String descripcion;
    private String categoria;
    private String unidadMedida;
    private Double precioUnitario;
    private LocalDate fechaVencimiento;
    private String proveedor;
    private String estado;
    private LocalDate fechaActualizacion;

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreMedicamento() {
        return nombre;
    }
    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombre = nombreMedicamento;
    }

    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getProveedor() {
        return proveedor;
    }
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }
    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}