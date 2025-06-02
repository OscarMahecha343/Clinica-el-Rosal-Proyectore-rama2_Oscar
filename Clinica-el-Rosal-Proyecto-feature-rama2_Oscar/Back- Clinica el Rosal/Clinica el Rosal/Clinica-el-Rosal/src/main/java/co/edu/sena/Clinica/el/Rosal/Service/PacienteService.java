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

    // GET: Obtener todos los pacientes
    public List<PacienteDTO> getAll() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // POST: Guardar paciente
    public void save(PacienteDTO dto) {
        PacienteEntity entity = new PacienteEntity();
        copyDtoToEntity(dto, entity);
        repository.save(entity);
    }
    

    // PUT: Actualizar paciente
    public void update(Long id, PacienteDTO dto) {
        PacienteEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + id));
        copyDtoToEntity(dto, entity);
        repository.save(entity);
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
}