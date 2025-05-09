package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.ConsultorioEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.ConsultorioRepository;
import co.edu.sena.Clinica.el.Rosal.dto.ConsultorioDTO;

@Service
public class ConsultorioService {

    @Autowired
    private ConsultorioRepository repository;

    // Guardar un nuevo consultorio
    public void save(ConsultorioDTO dto) {
        // Convertimos DTO a entidad para guardarla
        ConsultorioEntity entity = ConsultorioEntity.builder()
            .nombreConsultorio(dto.getNombreConsultorio())
            .ubicacion(dto.getUbicacion())
            .capacidad(dto.getCapacidad())
            .telefono(dto.getTelefono())
            .especialidad(dto.getEspecialidad())
            .estado(dto.getEstado())
            .build();

        repository.save(entity); // Guardamos en la BD
    }

    // Obtener todos los consultorios registrados
    public List<ConsultorioDTO> getAll() {
        return repository.findAll().stream().map(entity -> 
            ConsultorioDTO.builder()
                .id(entity.getId())
                .nombreConsultorio(entity.getNombreConsultorio())
                .ubicacion(entity.getUbicacion())
                .capacidad(entity.getCapacidad())
                .telefono(entity.getTelefono())
                .especialidad(entity.getEspecialidad())
                .estado(entity.getEstado())
                .build()
        ).toList();
    }

    // Eliminar un consultorio por su ID
    public void delete(Long id) {
        repository.deleteById(id); // Eliminación directa por ID
    }
}