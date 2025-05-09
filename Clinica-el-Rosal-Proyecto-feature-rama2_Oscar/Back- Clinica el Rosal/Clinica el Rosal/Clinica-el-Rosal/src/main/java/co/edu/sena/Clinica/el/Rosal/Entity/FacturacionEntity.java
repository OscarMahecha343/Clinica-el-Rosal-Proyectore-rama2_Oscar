package co.edu.sena.Clinica.el.Rosal.Entity;

import java.sql.Date;

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
@Table(name = "facturacion") // Tabla en la base de datos
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacturacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Clave primaria
    private Long id;

    @Column(name = "id_paciente") // Relación con entidad Paciente
    private Long idPaciente;

    @Column(name = "id_servicio") // Relación con entidad Servicio
    private Long idServicio;

    @Column(name = "monto") // Monto facturado
    private Double monto;

    @Column(name = "fecha") // Fecha de facturación
    private Date fecha;
}
