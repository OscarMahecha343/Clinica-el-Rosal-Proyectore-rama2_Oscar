package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.Optional;
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

    // Guardar nueva historia
    public void save(HistoriaDTO dto) {
        HistoriaEntity entity = new HistoriaEntity();
        entity.setIdPaciente(dto.getIdPaciente());
        entity.setIdMedico(dto.getIdMedico());
        entity.setFechaConsulta(dto.getFechaConsulta());
        entity.setMotivoConsulta(dto.getMotivoConsulta());
        entity.setHistorialClinico(dto.getHistorialClinico());
        entity.setDiagnostico(dto.getDiagnostico());
        entity.setTratamiento(dto.getTratamiento());
        entity.setAlergias(dto.getAlergias());
        entity.setAntecedentes(dto.getAntecedentes());
        entity.setSignosVitales(dto.getSignosVitales());
        entity.setExamenesSolicitados(dto.getExamenesSolicitados());
        repository.save(entity);
    }

    // Obtener todas las historias
    public List<HistoriaDTO> getAll() {
        List<HistoriaEntity> entities = repository.findAll();
        return entities.stream().map(entity -> HistoriaDTO.builder()
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

    // Actualizar una historia existente
    public void update(Long id, HistoriaDTO dto) {
        Optional<HistoriaEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            HistoriaEntity entity = optional.get();
            entity.setIdPaciente(dto.getIdPaciente());
            entity.setIdMedico(dto.getIdMedico());
            entity.setFechaConsulta(dto.getFechaConsulta());
            entity.setMotivoConsulta(dto.getMotivoConsulta());
            entity.setHistorialClinico(dto.getHistorialClinico());
            entity.setDiagnostico(dto.getDiagnostico());
            entity.setTratamiento(dto.getTratamiento());
            entity.setAlergias(dto.getAlergias());
            entity.setAntecedentes(dto.getAntecedentes());
            entity.setSignosVitales(dto.getSignosVitales());
            entity.setExamenesSolicitados(dto.getExamenesSolicitados());
            repository.save(entity);
        }
    }

    // Eliminar una historia por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
