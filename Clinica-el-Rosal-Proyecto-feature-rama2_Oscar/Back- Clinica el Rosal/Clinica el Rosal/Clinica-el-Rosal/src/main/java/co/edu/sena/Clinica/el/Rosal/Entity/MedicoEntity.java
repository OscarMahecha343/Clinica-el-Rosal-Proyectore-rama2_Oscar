package co.edu.sena.Clinica.el.Rosal.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import co.edu.sena.Clinica.el.Rosal.Entity.MedicoEntity;

@Entity
@Table(name = "medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  // Clave primaria
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombreMedico;

    @Column(name = "apellidos", nullable = false)
    private String apellidosMedicos;

    @Column(name = "telefono", nullable = false)
    private String telefonoDoc; 

    @Column(name = "licencia_medica", nullable = false)
    private String licenciaMedica; 

    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "direccion")
    private String direccion;

   @ManyToOne
    @JoinColumn(name = "id_especialidad")
    private EspecialidadEntity idEspecialidad;

    @ManyToOne
    @JoinColumn(name = "id_consultorio")
    private ConsultorioEntity consultorio;
}