package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.Clinica.el.Rosal.Service.ConsultorioService;
import co.edu.sena.Clinica.el.Rosal.dto.ConsultorioDTO;

@RestController
@RequestMapping("/consultorios") // Ruta base para los endpoints
public class ConsultorioController {

    @Autowired
    private ConsultorioService service;

    // Obtener todos los consultorios
    @GetMapping
    public List<ConsultorioDTO> getAll() {
        return service.getAll();
    }

    // Guardar un nuevo consultorio
    @PostMapping
    public void save(@RequestBody ConsultorioDTO dto) {
        service.save(dto);
    }

    // Eliminar un consultorio por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
