package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.SeguroEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.SeguroRepository;
import co.edu.sena.Clinica.el.Rosal.dto.SeguroDTO;

@Service
public class SeguroService {

    @Autowired
    private SeguroRepository repository;

    // Obtener todos los seguros
    public List<SeguroDTO> getAll() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Crear un nuevo seguro
    public void save(SeguroDTO dto) {
        SeguroEntity entity = SeguroEntity.builder()
                .nombre(dto.getNombre())
                .build();
        repository.save(entity);
    }

    // Actualizar seguro existente por ID
    public void update(Long id, SeguroDTO dto) {
        SeguroEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seguro no encontrado con ID: " + id));
        entity.setNombre(dto.getNombre());
        repository.save(entity);
    }

    // Eliminar seguro por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Conversión auxiliar de Entity a DTO
    private SeguroDTO convertToDTO(SeguroEntity entity) {
        return SeguroDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }
}