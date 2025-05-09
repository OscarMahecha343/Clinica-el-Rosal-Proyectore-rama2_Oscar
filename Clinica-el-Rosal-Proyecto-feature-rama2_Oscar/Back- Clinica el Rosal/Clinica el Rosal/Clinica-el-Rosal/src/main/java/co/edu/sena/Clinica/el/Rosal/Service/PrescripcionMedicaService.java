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

    public List<PrescripcionMedicaDTO> getAll() {
        return repository.findAll().stream().map(entity -> {
            PrescripcionMedicaDTO dto = new PrescripcionMedicaDTO();
            dto.setId(entity.getId());
            dto.setMedicamento(entity.getMedicamento());
            dto.setDosis(entity.getDosis());
            dto.setPacienteId(entity.getPacienteId());
            return dto;
        }).collect(Collectors.toList());
    }

    public void save(PrescripcionMedicaDTO dto) {
        PrescripcionMedicaEntity entity = new PrescripcionMedicaEntity();
        entity.setMedicamento(dto.getMedicamento());
        entity.setDosis(dto.getDosis());
        entity.setPacienteId(dto.getPacienteId());
        repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}