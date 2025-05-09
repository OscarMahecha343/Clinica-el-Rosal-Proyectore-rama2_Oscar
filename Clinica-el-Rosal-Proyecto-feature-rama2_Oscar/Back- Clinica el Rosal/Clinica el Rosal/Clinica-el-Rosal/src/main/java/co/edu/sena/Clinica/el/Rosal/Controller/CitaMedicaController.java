package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.sena.Clinica.el.Rosal.Service.CitaMedicaService;
import co.edu.sena.Clinica.el.Rosal.dto.CitaMedicaDTO;

@RestController
@RequestMapping("/cita")
public class CitaMedicaController { 

    @Autowired
    private CitaMedicaService service;

    // Obtener todas las citas médicas
    @GetMapping
    public List<CitaMedicaDTO> getAll() {
        return service.getAll();
    }

    // Guardar nueva cita médica
    @PostMapping
    public void save(@RequestBody CitaMedicaDTO dto) {
        service.save(dto);
    }

    // Eliminar cita médica por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}