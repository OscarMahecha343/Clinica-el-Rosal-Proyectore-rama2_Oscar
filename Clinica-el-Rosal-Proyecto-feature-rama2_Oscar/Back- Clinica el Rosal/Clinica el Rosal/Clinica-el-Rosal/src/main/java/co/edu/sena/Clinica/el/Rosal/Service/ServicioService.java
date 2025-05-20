package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.ServicioEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.ServicioRepository;
import co.edu.sena.Clinica.el.Rosal.dto.ServicioDTO;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository repository;

    // Obtener todos los servicios
    public List<ServicioDTO> getAll() {
        return repository.findAll().stream().map(entity -> ServicioDTO.builder()
                .id(entity.getId())
                .descripcionServicio(entity.getDescripcionServicio())
                .tipoServicio(entity.getTipoServicio())
                .build()).collect(Collectors.toList());
    }

    // Guardar un nuevo servicio
    public void save(ServicioDTO dto) {
        ServicioEntity entity = ServicioEntity.builder()
                .descripcionServicio(dto.getDescripcionServicio())
                .tipoServicio(dto.getTipoServicio())
                .build();
        repository.save(entity);
    }

    // Actualizar un servicio existente por ID
    public void update(Long id, ServicioDTO dto) {
        ServicioEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + id));
        entity.setDescripcionServicio(dto.getDescripcionServicio());
        entity.setTipoServicio(dto.getTipoServicio());
        repository.save(entity);
    }

    // Eliminar un servicio por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }
}