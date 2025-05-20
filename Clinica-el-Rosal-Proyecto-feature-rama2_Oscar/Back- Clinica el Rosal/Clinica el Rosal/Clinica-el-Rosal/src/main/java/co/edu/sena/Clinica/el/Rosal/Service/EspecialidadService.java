package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.EspecialidadEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.EspecialidadRepository;
import co.edu.sena.Clinica.el.Rosal.dto.EspecialidadDTO;

@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository repository;

    // Guardar nueva especialidad o actualizar existente
    public void save(EspecialidadDTO dto) {
        EspecialidadEntity entity = EspecialidadEntity.builder()
                .id(dto.getId()) // Si es null, se crea; si tiene ID, se actualiza
                .nombreEspecialidad(dto.getNombreEspecialidad())
                .build();
        repository.save(entity);
    }

    // Obtener lista de todas las especialidades
    public List<EspecialidadDTO> getAll() {
        return repository.findAll().stream().map(entity ->
            EspecialidadDTO.builder()
                    .id(entity.getId())
                    .nombreEspecialidad(entity.getNombreEspecialidad())
                    .build()
        ).collect(Collectors.toList());
    }

    // Obtener especialidad por ID
    public EspecialidadDTO getById(Long id) {
        return repository.findById(id).map(entity ->
            EspecialidadDTO.builder()
                    .id(entity.getId())
                    .nombreEspecialidad(entity.getNombreEspecialidad())
                    .build()
        ).orElse(null);
    }

    // Eliminar especialidad por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
