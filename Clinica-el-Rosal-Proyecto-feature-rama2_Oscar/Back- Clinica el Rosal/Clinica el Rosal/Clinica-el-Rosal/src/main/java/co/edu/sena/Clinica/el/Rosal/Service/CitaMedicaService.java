package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.CitaMedicaEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.CitaMedicaRepository;
import co.edu.sena.Clinica.el.Rosal.dto.CitaMedicaDTO;

@Service
public class CitaMedicaService {

    @Autowired
    private CitaMedicaRepository repository;

    // Guardar una nueva cita médica
    public void save(CitaMedicaDTO dto) {
        CitaMedicaEntity entity = CitaMedicaEntity.builder()
                .idPaciente(dto.getIdPaciente())
                .idMedico(dto.getIdMedico())
                .fecha(dto.getFecha())
                .hora(dto.getHora())
                .estado(dto.getEstado())
                .idEspecialidad(dto.getIdEspecialidad())
                .build();

        repository.save(entity);
    }

    // Obtener todas las citas médicas registradas
    public List<CitaMedicaDTO> getAll() {
        return repository.findAll().stream().map(entity -> CitaMedicaDTO.builder()
                .id(entity.getId())
                .idPaciente(entity.getIdPaciente())
                .idMedico(entity.getIdMedico())
                .fecha(entity.getFecha())
                .hora(entity.getHora())
                .estado(entity.getEstado())
                .idEspecialidad(entity.getIdEspecialidad())
                .build()).collect(Collectors.toList());
    }

    // Eliminar una cita médica por su ID
    public void delete(Long id) {
        repository.deleteById(id);
    }
}