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

@Service
public class DetalleExamenService {

    @Autowired
    private DetalleExamenRepository repository;

    @Autowired
    private TipoExamenRepository tipoExamenRepository;

    // Guardar o actualizar un examen
    public void save(DetalleExamenDTO dto) {
        TipoExamenEntity tipo = tipoExamenRepository.findById(dto.getIdTipoExamen()).orElse(null);

        DetalleExamenEntity entity = DetalleExamenEntity.builder()
                .id(dto.getId()) // Solo si es actualización
                .tipoExamen(tipo) // Relación ManyToOne
                .fechaExamen(dto.getFechaExamen())
                .archivoExamen(dto.getArchivoExamen())
                .idPaciente(dto.getIdPaciente())
                .idAuxiliar(dto.getIdAuxiliar())
                .createdAt(dto.getCreatedAt())
                .build();

        repository.save(entity);
    }

    // Obtener todos los exámenes por paciente
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

    // Obtener un examen por ID
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

    // Eliminar un examen por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }
}