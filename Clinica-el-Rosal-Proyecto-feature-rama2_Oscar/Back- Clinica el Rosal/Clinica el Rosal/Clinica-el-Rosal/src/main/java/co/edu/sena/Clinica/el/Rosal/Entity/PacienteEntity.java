package co.edu.sena.Clinica.el.Rosal.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "paciente" )
@Data
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
    private Double tipoIdentificacion;

    @Column(name = "identificacion")
    private Double identificacion;

    @Column(name = "id_seguro")
    private Long idSeguro;

    @Column(name = "telefono")
    private Double telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "grupo_sangineo")
    private String grupo_sangineo;

    @Column(name = "alergias")
    private String alergias;

    @Column(name = "Tipo_de_Alergia")
    private String TipoAlergia;

    @Column(name = "id_municipio")
    private Long idMunicipio;
}