package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.EstadoAfiliacionEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.PacienteEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.EstadoAfiliacionRepository;
import co.edu.sena.Clinica.el.Rosal.dto.EstadoAfiliacionDTO;

@Service
public class EstadoAfiliacionService {

    @Autowired
    private EstadoAfiliacionRepository repository;

    // Guardar nuevo registro o actualizar existente
    public void save(EstadoAfiliacionDTO dto) {
        EstadoAfiliacionEntity entity = EstadoAfiliacionEntity.builder()
                .id(dto.getId())
                .afiliacion(PacienteEntity.builder().id(dto.getIdAfiliacion()).build()) // solo id para referenciar
                .estadoAfiliacion(dto.getEstadoAfiliacion())
                .fechaActivacion(dto.getFechaActivacion())
                .fechaCertificado(dto.getFechaCertificado())
                .observaciones(dto.getObservaciones())
                .build();

        repository.save(entity);
    }

    // Obtener todos los registros
   public List<EstadoAfiliacionDTO> getAll() {
    return repository.findAll().stream()
        .map(entity -> EstadoAfiliacionDTO.builder()
            .id(entity.getId())
            .idAfiliacion(entity.getAfiliacion() != null ? entity.getAfiliacion().getId() : null)
            .nombreAfiliado(entity.getAfiliacion() != null ? entity.getAfiliacion().getNombrePaci() : null)
            .apellidoAfiliado(entity.getAfiliacion() != null ? entity.getAfiliacion().getApellidoPaci() : null)
            .estadoAfiliacion(entity.getEstadoAfiliacion())
            .fechaActivacion(entity.getFechaActivacion())
            .fechaCertificado(entity.getFechaCertificado())
            .observaciones(entity.getObservaciones())
            .build()
        ).collect(Collectors.toList());
}

    // Obtener registro por ID
    public EstadoAfiliacionDTO getByAfiliacionId(Long idAfiliado) {
    return repository.findByAfiliacion_Id(idAfiliado)
        .map(entity -> EstadoAfiliacionDTO.builder()
            .id(entity.getId())
            .idAfiliacion(entity.getAfiliacion() != null ? entity.getAfiliacion().getId() : null)
            .nombreAfiliado(entity.getAfiliacion() != null ? entity.getAfiliacion().getNombrePaci() : null)
            .apellidoAfiliado(entity.getAfiliacion() != null ? entity.getAfiliacion().getApellidoPaci() : null)
            .estadoAfiliacion(entity.getEstadoAfiliacion())
            .fechaActivacion(entity.getFechaActivacion())
            .fechaCertificado(entity.getFechaCertificado())
            .observaciones(entity.getObservaciones())
            .build()
        ).orElse(null);
}

public EstadoAfiliacionDTO getById(Long id) {
    return repository.findById(id)
        .map(entity -> EstadoAfiliacionDTO.builder()
            .id(entity.getId())
            .idAfiliacion(entity.getAfiliacion() != null ? entity.getAfiliacion().getId() : null)
            .nombreAfiliado(entity.getAfiliacion() != null ? entity.getAfiliacion().getNombrePaci() : null)
            .apellidoAfiliado(entity.getAfiliacion() != null ? entity.getAfiliacion().getApellidoPaci() : null)
            .estadoAfiliacion(entity.getEstadoAfiliacion())
            .fechaActivacion(entity.getFechaActivacion())
            .fechaCertificado(entity.getFechaCertificado())
            .observaciones(entity.getObservaciones())
            .build()
        ).orElse(null);
}


    // Eliminar por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
