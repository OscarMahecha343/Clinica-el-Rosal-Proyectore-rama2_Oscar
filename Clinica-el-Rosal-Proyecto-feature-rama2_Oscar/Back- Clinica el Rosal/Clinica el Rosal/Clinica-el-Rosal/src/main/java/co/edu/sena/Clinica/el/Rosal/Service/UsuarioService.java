package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.UsuarioEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.UsuarioRepository;
import co.edu.sena.Clinica.el.Rosal.dto.UsuarioDTO;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<UsuarioDTO> getAll() {
        return repository.findAll().stream().map(entity -> {
            UsuarioDTO dto = new UsuarioDTO();
            dto.setId(entity.getId());
            dto.setUsername(entity.getUsername());
            dto.setPassword(entity.getPassword());
            dto.setCorreo(entity.getCorreo());
            return dto;
        }).collect(Collectors.toList());
    }

    public void save(UsuarioDTO dto) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setCorreo(dto.getCorreo());
        repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
