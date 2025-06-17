package co.edu.sena.Clinica.el.Rosal.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.AuxiliarEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.DetalleExamenEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.PacienteEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.TipoExamenEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.AuxiliarRepository;
import co.edu.sena.Clinica.el.Rosal.Repository.DetalleExamenRepository;
import co.edu.sena.Clinica.el.Rosal.Repository.PacienteRepository;
import co.edu.sena.Clinica.el.Rosal.Repository.TipoExamenRepository;
import co.edu.sena.Clinica.el.Rosal.dto.DetalleExamenDTO;
import co.edu.sena.Clinica.el.Rosal.dto.TipoExamenDTO;

@Service
public class DetalleExamenService {

    @Autowired
    private DetalleExamenRepository repository;

    @Autowired
    private TipoExamenRepository tipoExamenRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AuxiliarRepository auxiliarRepository;

    // Crear o actualizar un examen
    public void save(DetalleExamenDTO dto) {
    try {
        // Guardar archivo en una carpeta local (ajústalo si usas otra ubicación)
        if (dto.getArchivo() != null && !dto.getArchivo().isEmpty()) {
            String nombreArchivo = UUID.randomUUID().toString() + "_" + dto.getArchivo().getOriginalFilename();
            Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo);
            Files.createDirectories(rutaArchivo.getParent()); // crea carpeta si no existe
            Files.copy(dto.getArchivo().getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);
            dto.setArchivoExamen(nombreArchivo);
        }

        // Buscar relaciones
        TipoExamenEntity tipo = tipoExamenRepository.findById(dto.getIdTipoExamen()).orElse(null);
        PacienteEntity paciente = pacienteRepository.findById(dto.getIdPaciente()).orElse(null);
        AuxiliarEntity auxiliar = auxiliarRepository.findById(dto.getIdAuxiliar()).orElse(null);

        // Crear entidad
        DetalleExamenEntity entity = DetalleExamenEntity.builder()
                .id(dto.getId())
                .tipoExamen(tipo)
                .fechaExamen(dto.getFechaExamen())
                .archivoExamen(dto.getArchivoExamen()) // nombre del archivo guardado
                .paciente(paciente)
                .auxiliar(auxiliar)
                .createdAt(dto.getCreatedAt())
                .build();

        repository.save(entity);
    } catch (IOException e) {
        throw new RuntimeException("❌ Error al guardar archivo del examen", e);
    }
}

    // Obtener exámenes por ID de paciente
    public List<DetalleExamenDTO> getByPacienteId(Long idPaciente) {
        return repository.findByPaciente_Id(idPaciente).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Obtener un examen por su ID
    public DetalleExamenDTO getById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    // Eliminar examen
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Obtener todos los tipos de examen
    public List<TipoExamenDTO> findAllTipoExamenes() {
        return tipoExamenRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Mapear entidad a DTO
    public DetalleExamenDTO mapToDTO(DetalleExamenEntity entity) {
        DetalleExamenDTO dto = new DetalleExamenDTO();
        dto.setId(entity.getId());
        dto.setFechaExamen(entity.getFechaExamen());
        dto.setArchivoExamen(entity.getArchivoExamen());
        dto.setCreatedAt(entity.getCreatedAt());

        if (entity.getTipoExamen() != null) {
            dto.setIdTipoExamen(entity.getTipoExamen().getId());
            dto.setNombreTipoExamen(entity.getTipoExamen().getNombre());
        }

        if (entity.getPaciente() != null) {
            dto.setIdPaciente(entity.getPaciente().getId());
            dto.setNombrePaciente(entity.getPaciente().getNombrePaci() + " " + entity.getPaciente().getApellidoPaci());
        }

        if (entity.getAuxiliar() != null) {
            dto.setIdAuxiliar(entity.getAuxiliar().getId());
            dto.setNombreAuxiliar(entity.getAuxiliar().getNombreAuxiliar() + " " + entity.getAuxiliar().getApellidoAuxiliar());
        }

        return dto;
    }

    // Mapear TipoExamenEntity a DTO
    private TipoExamenDTO toDTO(TipoExamenEntity entity) {
        return TipoExamenDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }
}