package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.DepartamentoEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.DepartamentoRepository;
import co.edu.sena.Clinica.el.Rosal.dto.DepartamentoDTO;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository repository;

    // Guardar un nuevo departamento
    public void save(DepartamentoDTO dto) {
        // Convertimos DTO a entidad para guardar en la BD
        DepartamentoEntity entity = DepartamentoEntity.builder()
            .nombreDepartamento(dto.getNombre())
            .build();

        repository.save(entity);
    }

    // Obtener todos los departamentos
    public List<DepartamentoDTO> getAll() {
        // Convertimos la lista de entidades a DTOs
        return repository.findAll().stream().map(entity ->
            DepartamentoDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombreDepartamento())
                .build()
        ).toList();
    }

    // Eliminar un departamento por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }
}