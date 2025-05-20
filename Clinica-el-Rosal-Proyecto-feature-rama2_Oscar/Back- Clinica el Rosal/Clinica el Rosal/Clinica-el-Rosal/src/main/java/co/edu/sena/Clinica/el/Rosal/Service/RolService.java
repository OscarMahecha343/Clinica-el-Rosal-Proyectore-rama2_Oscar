package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.RolEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.RolRepository;
import co.edu.sena.Clinica.el.Rosal.dto.RolDTO;

@Service
public class RolService {

    @Autowired
    private RolRepository repository;

    // Obtener todos los roles
    public List<RolDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Guardar nuevo rol
    public void save(RolDTO dto) {
        RolEntity entity = new RolEntity();
        entity.setNombre(dto.getNombre());
        repository.save(entity);
    }

    // Actualizar un rol existente
    public void update(Long id, RolDTO dto) {
        RolEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
        entity.setNombre(dto.getNombre());
        repository.save(entity);
    }

    // Eliminar un rol por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Método auxiliar para convertir entidad a DTO
    private RolDTO convertToDto(RolEntity entity) {
        return RolDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }
}
