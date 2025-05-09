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

import co.edu.sena.Clinica.el.Rosal.Service.DetalleExamenService;
import co.edu.sena.Clinica.el.Rosal.dto.DetalleExamenDTO;

@RestController
@RequestMapping("/api/examenes")
@CrossOrigin(origins = "*") // Permitir llamadas desde cualquier origen (útil para frontend externo)
public class DetalleExamenController {

    @Autowired
    private DetalleExamenService service;

    @GetMapping
    public List<DetalleExamenDTO> getAll() {
        return service.getAll(); // Retorna todos los exámenes
    }

    @GetMapping("/{id}")
    public DetalleExamenDTO getById(@PathVariable Long id) {
        return service.getById(id); // Retorna un examen por ID
    }

    @PostMapping
    public void save(@RequestBody DetalleExamenDTO dto) {
        service.save(dto); // Guarda un nuevo examen
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody DetalleExamenDTO dto) {
        dto.setId(id); // Asegura que el ID sea correcto
        service.save(dto); // Actualiza el examen
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id); // Elimina un examen por ID
    }
}
