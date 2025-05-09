package co.edu.sena.Clinica.el.Rosal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.AuxiliarEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.AuxiliarRepository;
import co.edu.sena.Clinica.el.Rosal.dto.AuxiliarDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuxiliarService {

    @Autowired
    private AuxiliarRepository repository;

    // Guardar un nuevo auxiliar
    public void save(AuxiliarDTO dto) {
        AuxiliarEntity entity = new AuxiliarEntity();

        entity.setNombreAuxiliar(dto.getNombreAuxiliar());
        entity.setApellidoAuxiliar(dto.getApellidoAuxiliar());
        entity.setIdentificacion(dto.getIdentificacion());
        entity.setTelefono(dto.getTelefono());
        entity.setCorreo(dto.getCorreo());
        entity.setDireccion(dto.getDireccion());

        repository.save(entity);
    }

    // Obtener todos los auxiliares
    public List<AuxiliarDTO> getAll() {
        return repository.findAll().stream().map(entity -> AuxiliarDTO.builder()
                .id(entity.getId())
                .nombreAuxiliar(entity.getNombreAuxiliar())
                .apellidoAuxiliar(entity.getApellidoAuxiliar())
                .identificacion(entity.getIdentificacion())
                .telefono(entity.getTelefono())
                .correo(entity.getCorreo())
                .direccion(entity.getDireccion())
                .build()
        ).collect(Collectors.toList());
    }

    // Eliminar auxiliar por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

