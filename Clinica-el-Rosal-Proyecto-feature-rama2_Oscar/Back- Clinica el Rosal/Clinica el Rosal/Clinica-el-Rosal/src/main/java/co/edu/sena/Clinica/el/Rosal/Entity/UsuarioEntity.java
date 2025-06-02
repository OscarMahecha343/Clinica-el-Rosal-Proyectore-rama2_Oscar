package co.edu.sena.Clinica.el.Rosal.Entity;

import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

    @OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_paciente")
    private PacienteEntity idPaciente;

    @OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_medico")
    private MedicoEntity idMedico;

    @OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_auxiliar")
    private AuxiliarEntity idAuxiliar;

    @OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_farmaceutico")
    private FarmaceuticoEntity idFarmaceutico;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity idRol;

    @Column(name = "codigo_restablecimiento")
    private String codigoRestablecimiento;

    @Column(name = "expiracion_codigo")
    private Date expiracionCodigo;

    @Column(name = "ultima_solicitud")
    private Date ultimaSolicitud;

    @Column(name = "intentos_fallidos")
    private int intentosFallidos;
}





