package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.PacienteEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.PacienteRepository;
import co.edu.sena.Clinica.el.Rosal.dto.PacienteDTO;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    // GET: Obtener todos los pacientes
    public List<PacienteDTO> getAll() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public PacienteEntity findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
    }

    public PacienteDTO findDtoById(Long id) {
    PacienteEntity entity = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
    return convertToDto(entity);
}
    
    // POST: Guardar paciente
    public PacienteDTO save(PacienteDTO dto) {
    // Validar si ya existe la identificación
    Optional<PacienteEntity> existente = repository.findByIdentificacion(dto.getIdentificacion());
    if (existente.isPresent()) {
        throw new RuntimeException("Ya existe un paciente con esa identificación.");
    }

    PacienteEntity entity = new PacienteEntity();
    copyDtoToEntity(dto, entity);
    PacienteEntity saved = repository.save(entity);
    return convertToDto(saved);
}

    // PUT: Actualizar paciente
    public PacienteDTO updateByIdentificacion(String identificacion, PacienteDTO dto) {
    PacienteEntity existente = repository.findByIdentificacion(identificacion)
        .orElseThrow(() -> new RuntimeException("Paciente no encontrado."));

    // Actualiza los campos necesarios
    existente.setNombrePaci(dto.getNombrePaci());
    existente.setApellidoPaci(dto.getApellidoPaci());
    existente.setTipoIdentificacion(dto.getTipoIdentificacion());
    existente.setGenero(dto.getGenero());
    existente.setFechaNacimiento(dto.getFechaNacimiento());
    existente.setTelefono(dto.getTelefono());
    existente.setCorreo(dto.getCorreo());
    existente.setDireccion(dto.getDireccion());
    existente.setIdMunicipio(dto.getIdMunicipio());
    existente.setIdSeguro(dto.getIdSeguro());
    existente.setGrupoSangineo(dto.getGrupoSangineo());
    existente.setAlergias(dto.getAlergias());
    existente.setTipoAlergia(dto.getTipoAlergia());

    repository.save(existente);

    return convertToDto(existente);
}

    // DELETE: Eliminar paciente
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Método auxiliar para copiar del DTO al Entity
    private void copyDtoToEntity(PacienteDTO dto, PacienteEntity entity) {
        entity.setNombrePaci(dto.getNombrePaci());
        entity.setApellidoPaci(dto.getApellidoPaci());
        entity.setGenero(dto.getGenero());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setTipoIdentificacion(dto.getTipoIdentificacion());
        entity.setIdentificacion(dto.getIdentificacion());
        entity.setIdSeguro(dto.getIdSeguro());
        entity.setTelefono(dto.getTelefono());
        entity.setCorreo(dto.getCorreo());
        entity.setDireccion(dto.getDireccion());
        entity.setGrupoSangineo(dto.getGrupoSangineo());
        entity.setAlergias(dto.getAlergias());
        entity.setTipoAlergia(dto.getTipoAlergia());
        entity.setIdMunicipio(dto.getIdMunicipio());
    }

    // Método auxiliar para convertir Entity a DTO
    private PacienteDTO convertToDto(PacienteEntity entity) {
        return PacienteDTO.builder()
                .id(entity.getId())
                .nombrePaci(entity.getNombrePaci())
                .apellidoPaci(entity.getApellidoPaci())
                .genero(entity.getGenero())
                .fechaNacimiento(entity.getFechaNacimiento())
                .tipoIdentificacion(entity.getTipoIdentificacion())
                .identificacion(entity.getIdentificacion())
                .idSeguro(entity.getIdSeguro())
                .telefono(entity.getTelefono())
                .correo(entity.getCorreo())
                .direccion(entity.getDireccion())
                .grupoSangineo(entity.getGrupoSangineo())
                .alergias(entity.getAlergias())
                .tipoAlergia(entity.getTipoAlergia())
                .idMunicipio(entity.getIdMunicipio())
                .build();
    }
    

    public PacienteDTO getByIdentificacion(String identificacion) {
        PacienteEntity entity = repository.findByIdentificacion(identificacion)
                .orElseThrow(
                        () -> new RuntimeException("Paciente no encontrado con la identificación: " + identificacion));
        return convertToDto(entity);
    }

    
}
