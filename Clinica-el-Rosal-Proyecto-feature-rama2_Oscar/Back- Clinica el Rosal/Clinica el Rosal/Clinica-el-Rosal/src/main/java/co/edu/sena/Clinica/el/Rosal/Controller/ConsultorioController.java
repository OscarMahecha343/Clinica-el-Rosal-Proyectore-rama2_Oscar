package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.sena.Clinica.el.Rosal.Service.ConsultorioService;
import co.edu.sena.Clinica.el.Rosal.dto.ConsultorioDTO;

@RestController
@RequestMapping("/consultorio") // Ruta base del recurso consultorio
public class ConsultorioController {

    @Autowired
    private ConsultorioService service;

    // GET - Listar todos los consultorios
    @GetMapping
    public List<ConsultorioDTO> getAll() {
        return service.getAll();
    }

    // POST - Guardar nuevo consultorio
    @PostMapping
    public void save(@RequestBody ConsultorioDTO dto) {
        service.save(dto);
    }

    // DELETE - Eliminar consultorio por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}