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
@Table(name = "historia") 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class HistoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
    private Long id;
  
    @Column(name = "id_paciente")
    private Long idPaciente;

    @Column(name = "id_medico")
    private Long idMedico;

    @Column(name = "fecha_consulta")
    private Date fechaConsulta;

    @Column(name = "motivo_consulta")
    private String motivoConsulta;

    @Column(name = "historialClinico")
    private String historialClinico;

    @Column(name = "diagnostico")
    private String diagnostico;

    @Column(name = "tratamiento")
    private String tratamiento;

    @Column(name = "alergias")
    private String alergias;

    @Column(name = "antecedentes")
    private String antecedentes;

    @Column(name = "signos_vitales")
    private String signosVitales;

    @Column(name = "examenes_solicitados")
    private String examenesSolicitados;

}