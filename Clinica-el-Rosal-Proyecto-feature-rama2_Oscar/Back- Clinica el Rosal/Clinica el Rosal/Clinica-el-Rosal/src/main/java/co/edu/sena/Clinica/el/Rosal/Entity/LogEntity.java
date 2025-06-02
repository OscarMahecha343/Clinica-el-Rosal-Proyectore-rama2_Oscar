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
@Table(name = "rol")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
    
    private Long id;

    @Column(name = "referencia") 
    private String referencia;
    
    @Column(name = "data") 
    private String data;

    @Column(name = "fecha") 
    private Date fecha;

    @Column(name = "id_usuario") 
    private Long idUsuario;

    @Column(name = "estado") 
    private String estado;   
    
}
			