package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
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

    // Obtener todos los pacientes en formato DTO
    public List<PacienteDTO> getAll() {
        return repository.findAll().stream().map(entity -> PacienteDTO.builder()
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
                .grupo_sangineo(entity.getGrupo_sangineo())
                .alergias(entity.getAlergias())
                .TipoAlergia(entity.getTipoAlergia())
                .idMunicipio(entity.getIdMunicipio())
                .build()
        ).collect(Collectors.toList());
    }

    // Guardar nuevo paciente
    public void save(PacienteDTO dto) {
        PacienteEntity entity = new PacienteEntity();
        copyDtoToEntity(dto, entity);
        repository.save(entity);
    }

    // Actualizar paciente existente por ID
    public void update(Long id, PacienteDTO dto) {
        PacienteEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + id));

        copyDtoToEntity(dto, entity);
        entity.setId(id); // aseguramos que es actualización
        repository.save(entity);
    }

    // Eliminar paciente por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Método auxiliar para copiar datos del DTO al Entity
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
        entity.setGrupo_sangineo(dto.getGrupo_sangineo());
        entity.setAlergias(dto.getAlergias());
        entity.setTipoAlergia(dto.getTipoAlergia());
        entity.setIdMunicipio(dto.getIdMunicipio());
    }
}
