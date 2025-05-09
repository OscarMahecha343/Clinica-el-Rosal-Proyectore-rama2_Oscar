package co.edu.sena.Clinica.el.Rosal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.AfiliacionEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.AfiliacionRepository;
import co.edu.sena.Clinica.el.Rosal.dto.AfiliacionDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AfiliacionService {

    @Autowired
    private AfiliacionRepository repository;

    public void save(AfiliacionDTO dto) {
        AfiliacionEntity entity = new AfiliacionEntity();
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setTipoIdentificacion(dto.getTipoIdentificacion());
        entity.setIdentificacion(dto.getIdentificacion());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setTelefono(dto.getTelefono());
        entity.setCorreo(dto.getCorreo());
        entity.setDireccion(dto.getDireccion());
        entity.setIdMunicipio(dto.getIdMunicipio());
        entity.setTipoAfiliacion(dto.getTipoAfiliacion());
        entity.setIdSeguro(dto.getIdSeguro());

        repository.save(entity);
    }

    public List<AfiliacionDTO> findAll() {
        return repository.findAll().stream().map(entity -> AfiliacionDTO.builder()
            .id(entity.getId())
            .nombre(entity.getNombre())
            .apellido(entity.getApellido())
            .tipoIdentificacion(entity.getTipoIdentificacion())
            .identificacion(entity.getIdentificacion())
            .fechaNacimiento(entity.getFechaNacimiento())
            .telefono(entity.getTelefono())
            .correo(entity.getCorreo())
            .direccion(entity.getDireccion())
            .idMunicipio(entity.getIdMunicipio())
            .tipoAfiliacion(entity.getTipoAfiliacion())
            .idSeguro(entity.getIdSeguro())
            .build()
        ).collect(Collectors.toList());
    }

    public Optional<AfiliacionDTO> findById(Long id) {
        return repository.findById(id).map(entity -> AfiliacionDTO.builder()
            .id(entity.getId())
            .nombre(entity.getNombre())
            .apellido(entity.getApellido())
            .tipoIdentificacion(entity.getTipoIdentificacion())
            .identificacion(entity.getIdentificacion())
            .fechaNacimiento(entity.getFechaNacimiento())
            .telefono(entity.getTelefono())
            .correo(entity.getCorreo())
            .direccion(entity.getDireccion())
            .idMunicipio(entity.getIdMunicipio())
            .tipoAfiliacion(entity.getTipoAfiliacion())
            .idSeguro(entity.getIdSeguro())
            .build()
        );
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

