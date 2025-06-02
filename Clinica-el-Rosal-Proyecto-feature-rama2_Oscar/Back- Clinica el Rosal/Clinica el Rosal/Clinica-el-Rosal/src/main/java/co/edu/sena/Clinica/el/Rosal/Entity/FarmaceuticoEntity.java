package co.edu.sena.Clinica.el.Rosal.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "farmaceutico") 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmaceuticoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
    private Long id;

    @Column(name = "nombre") // Nombre del farmacéutico
    private String nombreFarmaceuta;

    @Column(name = "apellido") // Apellido del farmacéutico
    private String apellidoFarmaceuta;

    @Column(name = "numero_licencia") // Número de licencia profesional
    private String numeroLicencia;

    @Column(name = "telefono") // Teléfono de contacto
    private String telefonoFarmaceuta;

    @Column(name = "correo") // Correo electrónico
    private String correoFarmaceuta;

    @Column(name = "direccion") // Dirección del farmacéutico
    private String direccionFarmaceuta;

    @OneToOne
    @JoinColumn(name = "id")
    private UsuarioEntity usuario; 
}
