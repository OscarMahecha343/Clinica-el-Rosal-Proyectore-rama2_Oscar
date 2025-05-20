package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.sena.Clinica.el.Rosal.Service.AuxiliarService;
import co.edu.sena.Clinica.el.Rosal.dto.AuxiliarDTO;

@RestController
@RequestMapping("/auxiliar") // Ruta base de la API
@CrossOrigin(origins = "*") // Permite acceso desde cualquier origen (frontend o Postman)
public class AuxiliarController {

    @Autowired
    private AuxiliarService service;

    // Obtener todos los auxiliares (GET)
    @GetMapping
    public List<AuxiliarDTO> getAll() {
        return service.getAll();
    }

    // Guardar un nuevo auxiliar (POST)
    @PostMapping
    public void save(@RequestBody AuxiliarDTO dto) {
        service.save(dto);
    }

    // Eliminar auxiliar por ID (DELETE)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}