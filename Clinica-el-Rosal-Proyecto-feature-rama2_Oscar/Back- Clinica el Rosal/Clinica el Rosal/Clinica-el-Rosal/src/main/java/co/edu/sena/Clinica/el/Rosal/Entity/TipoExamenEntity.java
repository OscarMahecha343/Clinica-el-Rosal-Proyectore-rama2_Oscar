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
@Data
@Table(name = "tipo_examen")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoExamenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false) // El nombre no debe ser nulo
    private String nombre;
}