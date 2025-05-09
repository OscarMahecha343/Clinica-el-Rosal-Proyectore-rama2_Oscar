package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.RolEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.RolRepository;
import co.edu.sena.Clinica.el.Rosal.dto.RolDTO;

@Service
public class RolService {

    @Autowired
    private RolRepository repository;

    public List<RolDTO> getAll() {
        return repository.findAll().stream().map(entity -> {
            RolDTO dto = new RolDTO();
            dto.setId(entity.getId());
            dto.setNombre(entity.getNombre());
            return dto;
        }).collect(Collectors.toList());
    }

    public void save(RolDTO dto) {
        RolEntity entity = new RolEntity();
        entity.setNombre(dto.getNombre());
        repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
