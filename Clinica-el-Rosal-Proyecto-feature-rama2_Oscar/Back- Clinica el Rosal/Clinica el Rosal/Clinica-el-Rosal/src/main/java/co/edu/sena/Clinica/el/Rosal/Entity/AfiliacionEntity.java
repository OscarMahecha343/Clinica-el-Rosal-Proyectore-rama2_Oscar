package co.edu.sena.Clinica.el.Rosal.Entity;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "afiliacion")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AfiliacionEntity { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombres")
    private String nombre;

    @Column(name = "apellidos")
    private String apellido;

    @Column(name = "tipo_identificacion")
    private String tipoIdentificacion;

    @Column(name = "identificacion")
    private String identificacion;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "id_municipio")
    private String idMunicipio;

    @Column(name = "tipo_afiliacion")
    private String tipoAfiliacion;

    @Column(name = "id_seguro")
    private String idSeguro;
}