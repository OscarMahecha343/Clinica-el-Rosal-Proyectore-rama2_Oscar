package co.edu.sena.Clinica.el.Rosal.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departamento") // Asocia la clase con la tabla 'departamento' en la base de datos
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_departamento")
    private String nombreDepartamento; // Nombre del departamento
}