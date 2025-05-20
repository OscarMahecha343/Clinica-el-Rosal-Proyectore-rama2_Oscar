package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.PrescripcionMedicaEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.PrescripcionMedicaRepository;
import co.edu.sena.Clinica.el.Rosal.dto.PrescripcionMedicaDTO;

@Service
public class PrescripcionMedicaService {

    @Autowired
    private PrescripcionMedicaRepository repository;

    // Obtener todas las prescripciones médicas
    public List<PrescripcionMedicaDTO> getAll() {
        return repository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Guardar nueva prescripción médica
    public void save(PrescripcionMedicaDTO dto) {
        PrescripcionMedicaEntity entity = new PrescripcionMedicaEntity();
        copyDtoToEntity(dto, entity);
        repository.save(entity);
    }

    // Actualizar prescripción existente
    public void update(Long id, PrescripcionMedicaDTO dto) {
        PrescripcionMedicaEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescripción no encontrada con id: " + id));
        copyDtoToEntity(dto, entity);
        repository.save(entity);
    }

    // Eliminar prescripción por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Métodos auxiliares
    private void copyDtoToEntity(PrescripcionMedicaDTO dto, PrescripcionMedicaEntity entity) {
        entity.setIdHistoria(dto.getIdHistoria());
        entity.setIdMedicamentos(dto.getIdMedicamentos());
        entity.setCantidadTotal(dto.getCantidadTotal());
        entity.setPresentacion(dto.getPresentacion());
        entity.setIndicaciones(dto.getIndicaciones());
    }

    private PrescripcionMedicaDTO convertToDto(PrescripcionMedicaEntity entity) {
        return PrescripcionMedicaDTO.builder()
                .id(entity.getId())
                .idHistoria(entity.getIdHistoria())
                .idMedicamentos(entity.getIdMedicamentos())
                .cantidadTotal(entity.getCantidadTotal())
                .presentacion(entity.getPresentacion())
                .indicaciones(entity.getIndicaciones())
                .build();
    }
}
