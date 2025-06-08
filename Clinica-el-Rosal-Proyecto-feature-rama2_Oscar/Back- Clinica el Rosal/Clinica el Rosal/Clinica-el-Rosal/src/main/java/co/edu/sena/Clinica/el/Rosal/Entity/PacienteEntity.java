package co.edu.sena.Clinica.el.Rosal.Entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "paciente" )
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombrePaci;

    @Column(name = "apellido")
    private String apellidoPaci;

    @Column(name = "genero")
    private String genero;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    
    @Column(name = "tipo_identificacion")
    private String tipoIdentificacion;

    @Column(name = "identificacion")
    private String identificacion;

    @Column(name = "id_seguro")
    private Long idSeguro;

    @Column(name = "telefono")
    private String telefono; 

    @Column(name = "correo")
    private String correo;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "grupo_sangineo")
    private String grupoSangineo;

    @Column(name = "alergias")
    private String alergias;

    @Column(name = "tipo_de_alergia")
    private String tipoAlergia;

    @Column(name = "id_municipio")
    private Long idMunicipio;

    @OneToOne
    @JoinColumn(name = "id")
    private UsuarioEntity usuario; 

     @OneToMany(mappedBy = "idPaciente", cascade = CascadeType.ALL)
    private List<UsuarioEntity> usuarios;
}
