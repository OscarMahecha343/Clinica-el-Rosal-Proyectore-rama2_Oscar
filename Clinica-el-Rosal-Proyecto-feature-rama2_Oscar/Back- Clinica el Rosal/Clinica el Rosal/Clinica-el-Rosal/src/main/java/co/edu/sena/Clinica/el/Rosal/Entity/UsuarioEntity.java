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
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    // Relación con distintos perfiles (pueden ser null dependiendo del rol)
    @Column(name = "id_paciente", nullable = true)
    private Long idPaciente;

    @Column(name = "id_medico")
    private Long idMedico;

    @Column(name = "id_auxiliar")
    private Long idAuxiliar;

    @Column(name = "id_farmaceutico")
    private Long idFarmaceutico;

    @Column(name = "id_rol", nullable = false)
    private Long idRol;

    @Column(name = "codigo_restablecimiento")
    private String codigoRestablecimiento;

    @Column(name = "expiracion_codigo")
    private Date expiracionCodigo;

    @Column(name = "ultima_solicitud")
    private Date ultimaSolicitud;

    @Column(name = "intentos_fallidos")
    private int intentosFallidos;
}





