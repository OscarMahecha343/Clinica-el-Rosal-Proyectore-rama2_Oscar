package co.edu.sena.Clinica.el.Rosal.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "municipio")
@Data
public class MunicipioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  // Clave primaria autoincremental
    private Long id;

    @Column(name = "nombre")  // Nombre del municipio
    private String nombreMunicipio;

    @Column(name = "id_departamento")  // ID del departamento relacionado
    private Long idDepartamento;

    @Column(name = "estado")  // Estado del municipio (activo, inactivo, etc.)
    private String estado;
}