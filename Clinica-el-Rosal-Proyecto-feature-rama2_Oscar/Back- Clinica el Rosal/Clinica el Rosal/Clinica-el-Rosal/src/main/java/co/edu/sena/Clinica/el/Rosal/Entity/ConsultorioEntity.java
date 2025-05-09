package co.edu.sena.Clinica.el.Rosal.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "consultorio") // Mapeamos la clase con la tabla 'consultorio'
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultorioEntity { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre") // Nombre del consultorio
    private String nombreConsultorio;
    
    @Column(name = "ubicacion") // ID o código de ubicación
    private Long ubicacion;

    @Column(name = "capacidad") // Número de personas que puede atender
    private String capacidad;

    @Column(name = "telefono") // Teléfono del consultorio
    private String telefono;

    @Column(name = "especialidad") // Especialidad médica que se atiende
    private String especialidad;

    @Column(name = "estado") // Estado del consultorio (activo/inactivo)
    private String estado;
}
