package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.AuxiliarEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.AuxiliarRepository;
import co.edu.sena.Clinica.el.Rosal.dto.AuxiliarDTO;

@Service
public class AuxiliarService {

    @Autowired
    private AuxiliarRepository repository;

    // Obtener todos los auxiliares
    public List<AuxiliarDTO> getAll() {
        return repository.findAll().stream().map(aux -> AuxiliarDTO.builder()
                .id(aux.getId())
                .nombreAuxiliar(aux.getNombreAuxiliar())
                .apellidoAuxiliar(aux.getApellidoAuxiliar())
                .identificacion(aux.getIdentificacion())
                .telefono(aux.getTelefono())
                .correo(aux.getCorreo())
                .direccion(aux.getDireccion())
                .build()).collect(Collectors.toList());
    }

    // Guardar auxiliar
    public void save(AuxiliarDTO dto) {
        AuxiliarEntity entity = AuxiliarEntity.builder()
                .nombreAuxiliar(dto.getNombreAuxiliar())
                .apellidoAuxiliar(dto.getApellidoAuxiliar())
                .identificacion(dto.getIdentificacion())
                .telefono(dto.getTelefono())
                .correo(dto.getCorreo())
                .direccion(dto.getDireccion())
                .build();
        repository.save(entity);
    }

    // Eliminar auxiliar
    public void delete(Long id) {
        repository.deleteById(id);
    }
}