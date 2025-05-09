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

    public List<ServicioDTO> getAll() {
        return repository.findAll().stream().map(entity -> {
            ServicioDTO dto = new ServicioDTO();
            dto.setId(entity.getId());
            dto.setNombre(entity.getNombre());
            dto.setDescripcion(entity.getDescripcion());
            return dto;
        }).collect(Collectors.toList());
    }

    public void save(ServicioDTO dto) {
        ServicioEntity entity = new ServicioEntity();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}