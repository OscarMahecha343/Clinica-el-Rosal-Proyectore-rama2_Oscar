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
@Table(name = "auxiliar")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuxiliarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombreAuxiliar;

    @Column(name = "apellido")
    private String apellidoAuxiliar;

    @Column(name = "identificacion")
    private String identificacion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name =  "correo")
    private String correo;

    @Column(name = "direccion")
    private String direccion;

}
