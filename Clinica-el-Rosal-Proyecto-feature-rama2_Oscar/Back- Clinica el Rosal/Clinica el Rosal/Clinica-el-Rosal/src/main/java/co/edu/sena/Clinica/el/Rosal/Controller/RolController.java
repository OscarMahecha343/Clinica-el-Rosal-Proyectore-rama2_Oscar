package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.Clinica.el.Rosal.Service.RolService;
import co.edu.sena.Clinica.el.Rosal.dto.RolDTO;

@RestController
@RequestMapping("/rol")
@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen (útil para frontend local)

public class RolController {

    @Autowired
    private RolService service;

    // Obtener todos los roles
    @GetMapping
    public List<RolDTO> getAll() {
        return service.getAll();
    }

    // Crear un nuevo rol
    @PostMapping
    public void save(@RequestBody RolDTO dto) {
        service.save(dto);
    }

    // Actualizar un rol por ID
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody RolDTO dto) {
        service.update(id, dto);
    }

    // Eliminar un rol por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}