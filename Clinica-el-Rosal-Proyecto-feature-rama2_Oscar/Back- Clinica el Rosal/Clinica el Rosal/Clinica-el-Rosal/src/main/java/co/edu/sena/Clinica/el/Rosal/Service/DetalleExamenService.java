package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.sena.Clinica.el.Rosal.Entity.DetalleExamenEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.TipoExamenEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.DetalleExamenRepository;
import co.edu.sena.Clinica.el.Rosal.Repository.TipoExamenRepository;
import co.edu.sena.Clinica.el.Rosal.dto.DetalleExamenDTO;
import co.edu.sena.Clinica.el.Rosal.dto.TipoExamenDTO;

@Service
public class DetalleExamenService {

    @Autowired
    private DetalleExamenRepository repository;

    @Autowired
    private TipoExamenRepository tipoExamenRepository;

    public void save(DetalleExamenDTO dto) {
        TipoExamenEntity tipo = tipoExamenRepository.findById(dto.getIdTipoExamen()).orElse(null);

        DetalleExamenEntity entity = DetalleExamenEntity.builder()
                .id(dto.getId())
                .tipoExamen(tipo)
                .fechaExamen(dto.getFechaExamen())
                .archivoExamen(dto.getArchivoExamen())
                .idPaciente(dto.getIdPaciente())
                .idAuxiliar(dto.getIdAuxiliar())
                .createdAt(dto.getCreatedAt())
                .build();

        repository.save(entity);
    }

    public List<DetalleExamenDTO> getByPacienteId(Long idPaciente) {
        return repository.findByIdPaciente(idPaciente).stream().map(entity -> {
            TipoExamenEntity tipo = entity.getTipoExamen();
            return DetalleExamenDTO.builder()
                    .id(entity.getId())
                    .idTipoExamen(tipo != null ? tipo.getId() : null)
                    .nombreExamen(tipo != null ? tipo.getNombre() : "No especificado")
                    .fechaExamen(entity.getFechaExamen())
                    .archivoExamen(entity.getArchivoExamen())
                    .idPaciente(entity.getIdPaciente())
                    .idAuxiliar(entity.getIdAuxiliar())
                    .createdAt(entity.getCreatedAt())
                    .build();
        }).collect(Collectors.toList());
    }

    public DetalleExamenDTO getById(Long id) {
        return repository.findById(id).map(entity -> {
            TipoExamenEntity tipo = entity.getTipoExamen();
            return DetalleExamenDTO.builder()
                    .id(entity.getId())
                    .idTipoExamen(tipo != null ? tipo.getId() : null)
                    .nombreExamen(tipo != null ? tipo.getNombre() : "No especificado")
                    .fechaExamen(entity.getFechaExamen())
                    .archivoExamen(entity.getArchivoExamen())
                    .idPaciente(entity.getIdPaciente())
                    .idAuxiliar(entity.getIdAuxiliar())
                    .createdAt(entity.getCreatedAt())
                    .build();
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<TipoExamenDTO> findAllTipoExamenes() {
        return tipoExamenRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private TipoExamenDTO toDTO(TipoExamenEntity entity) {
        return TipoExamenDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }
}
