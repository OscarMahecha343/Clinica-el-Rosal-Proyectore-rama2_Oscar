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

    /** Clave primaria autoincremental */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** ID del paciente asociado a esta facturación */
    @Column(name = "id_paciente", nullable = false)
    private Long idPaciente;

    /** ID del servicio facturado */
    @Column(name = "id_servicio", nullable = false)
    private Long idServicio;

    /** Monto total de la facturación */
    @Column(name = "monto", nullable = false)
    private Double monto;

    /** Fecha en la que se emitió la factura */
    @Column(name = "fecha", nullable = false)
    private Date fecha;
}
