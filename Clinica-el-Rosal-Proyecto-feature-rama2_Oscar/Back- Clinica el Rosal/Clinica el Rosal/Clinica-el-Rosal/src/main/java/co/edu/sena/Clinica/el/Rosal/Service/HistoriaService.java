package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.HistoriaEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.HistoriaRepository;
import co.edu.sena.Clinica.el.Rosal.dto.HistoriaDTO;

@Service
public class HistoriaService {

    @Autowired
    private HistoriaRepository repository;

    // Crear o actualizar historia clínica
    public HistoriaDTO save(HistoriaDTO dto) {
        HistoriaEntity entity = HistoriaEntity.builder()
                .id(dto.getId())
                .idPaciente(dto.getIdPaciente())
                .idMedico(dto.getIdMedico())
                .fechaConsulta(dto.getFechaConsulta())
                .motivoConsulta(dto.getMotivoConsulta())
                .historialClinico(dto.getHistorialClinico())
                .diagnostico(dto.getDiagnostico())
                .tratamiento(dto.getTratamiento())
                .alergias(dto.getAlergias())
                .antecedentes(dto.getAntecedentes())
                .signosVitales(dto.getSignosVitales())
                .examenesSolicitados(dto.getExamenesSolicitados())
                .build();

        entity = repository.save(entity);
        dto.setId(entity.getId()); // actualiza el ID generado
        return dto;
    }

    // Obtener todas las historias clínicas
    public List<HistoriaDTO> getAll() {
        return repository.findAll().stream().map(entity -> HistoriaDTO.builder()
                .id(entity.getId())
                .idPaciente(entity.getIdPaciente())
                .idMedico(entity.getIdMedico())
                .fechaConsulta(entity.getFechaConsulta())
                .motivoConsulta(entity.getMotivoConsulta())
                .historialClinico(entity.getHistorialClinico())
                .diagnostico(entity.getDiagnostico())
                .tratamiento(entity.getTratamiento())
                .alergias(entity.getAlergias())
                .antecedentes(entity.getAntecedentes())
                .signosVitales(entity.getSignosVitales())
                .examenesSolicitados(entity.getExamenesSolicitados())
                .build()
        ).collect(Collectors.toList());
    }

    // Obtener una historia por ID
    public HistoriaDTO getById(Long id) {
        HistoriaEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historia no encontrada con ID: " + id));

        return HistoriaDTO.builder()
                .id(entity.getId())
                .idPaciente(entity.getIdPaciente())
                .idMedico(entity.getIdMedico())
                .fechaConsulta(entity.getFechaConsulta())
                .motivoConsulta(entity.getMotivoConsulta())
                .historialClinico(entity.getHistorialClinico())
                .diagnostico(entity.getDiagnostico())
                .tratamiento(entity.getTratamiento())
                .alergias(entity.getAlergias())
                .antecedentes(entity.getAntecedentes())
                .signosVitales(entity.getSignosVitales())
                .examenesSolicitados(entity.getExamenesSolicitados())
                .build();
    }

    // Eliminar una historia clínica por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }
}