package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.DepartamentoEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.DepartamentoRepository;
import co.edu.sena.Clinica.el.Rosal.dto.DepartamentoDTO;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository repository;

    // Crear o actualizar un departamento
    public void save(DepartamentoDTO dto) {
        DepartamentoEntity entity = DepartamentoEntity.builder()
                .id(dto.getId()) // Permite actualización si se pasa un ID
                .nombreDepartamento(dto.getNombreDepartamento())
                .build();

        repository.save(entity); // Si el ID ya existe, actualiza
    }

    // Obtener todos los departamentos
    public List<DepartamentoDTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> DepartamentoDTO.builder()
                        .id(entity.getId())
                        .nombreDepartamento(entity.getNombreDepartamento())
                        .build())
                .collect(Collectors.toList());
    }

    // Eliminar por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Obtener un departamento por ID (opcional, útil para ediciones)
    public Optional<DepartamentoDTO> getById(Long id) {
        return repository.findById(id)
                .map(entity -> DepartamentoDTO.builder()
                        .id(entity.getId())
                        .nombreDepartamento(entity.getNombreDepartamento())
                        .build());
    }
}