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
@Table(name = "medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoEntity { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombreMedico;

    @Column(name = "apellidos")
    private String apellidosMedicos;

    @Column(name = "telefono")
    private Double telefonoDoc;

    @Column(name = "licencia_medica")
    private Double licenciaMedica;

    @Column(name = "id_especialidad")
    private String idEspecialidad;

    @Column(name = "correo")
    private String correo;

    @Column(name = "direccion")
    private Long direccion;

    @Column(name = "id_consultorio")
    private Long consultorio;


}