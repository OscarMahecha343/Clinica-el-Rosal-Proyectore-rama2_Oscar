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
@Table(name = "agendamiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AgendamientoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha")
    private String fechaAgendamiento;

    @Column(name = "hora")
    private String horaAgendamiento;

    @Column(name = "id_paciente")
    private String idPaciente;

    @Column(name =  "id_medico")
    private String idMedico;

    @Column(name = "id_especialidad")
    private String idEspecialidad;

    @Column(name = "sede")
    private String sede;

    @Column(name = "estado")
    private String estadoDisponibilidad;

    @Column(name = "motivo")
    private String motivoConsulta;

    @Column(name =  "id_usuario_creador")
    private String id_Usuario_creador;

    @Column(name = "tipo_creador")
    private String tipoCreador;

}
