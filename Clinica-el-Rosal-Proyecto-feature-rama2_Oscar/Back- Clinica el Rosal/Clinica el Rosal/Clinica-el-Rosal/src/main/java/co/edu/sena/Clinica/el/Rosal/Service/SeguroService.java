package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.SeguroEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.SeguroRepository;
import co.edu.sena.Clinica.el.Rosal.dto.SeguroDTO;

@Service
public class SeguroService {

    @Autowired
    private SeguroRepository repository;

    public List<SeguroDTO> getAll() {
        return repository.findAll().stream().map(entity -> {
            SeguroDTO dto = new SeguroDTO();
            dto.setId(entity.getId());
            dto.setNombre(entity.getNombre());
            dto.setTipo(entity.getTipo());
            return dto;
        }).collect(Collectors.toList());
    }

    public void save(SeguroDTO dto) {
        SeguroEntity entity = new SeguroEntity();
        entity.setNombre(dto.getNombre());
        entity.setTipo(dto.getTipo());
        repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}