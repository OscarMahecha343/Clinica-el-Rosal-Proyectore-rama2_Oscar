package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.sena.Clinica.el.Rosal.Entity.DetalleExamenEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.DetalleExamenRepository;
import co.edu.sena.Clinica.el.Rosal.dto.DetalleExamenDTO;

@Service
public class DetalleExamenService {

    @Autowired
    private DetalleExamenRepository repository;

    // Guardar o actualizar un examen
    public void save(DetalleExamenDTO dto) {
        DetalleExamenEntity entity = DetalleExamenEntity.builder()
                .id(dto.getId()) // Solo si es actualización
                .idTipoExamen(dto.getIdTipoExamen())
                .fechaExamen(dto.getFechaExamen())
                .archivoExamen(dto.getArchivoExamen())
                .idPaciente(dto.getIdPaciente())
                .idAuxiliar(dto.getIdAuxiliar())
                .createdAt(dto.getCreatedAt())
                .build();
        repository.save(entity);
    }

    // Obtener todos los exámenes
    public List<DetalleExamenDTO> getAll() {
        return repository.findAll().stream().map(entity ->
                DetalleExamenDTO.builder()
                        .id(entity.getId())
                        .idTipoExamen(entity.getIdTipoExamen())
                        .fechaExamen(entity.getFechaExamen())
                        .archivoExamen(entity.getArchivoExamen())
                        .idPaciente(entity.getIdPaciente())
                        .idAuxiliar(entity.getIdAuxiliar())
                        .createdAt(entity.getCreatedAt())
                        .build()
        ).collect(Collectors.toList());
    }

    // Obtener un examen por ID
    public DetalleExamenDTO getById(Long id) {
        return repository.findById(id).map(entity ->
                DetalleExamenDTO.builder()
                        .id(entity.getId())
                        .idTipoExamen(entity.getIdTipoExamen())
                        .fechaExamen(entity.getFechaExamen())
                        .archivoExamen(entity.getArchivoExamen())
                        .idPaciente(entity.getIdPaciente())
                        .idAuxiliar(entity.getIdAuxiliar())
                        .createdAt(entity.getCreatedAt())
                        .build()
        ).orElse(null);
    }

    // Eliminar un examen por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
