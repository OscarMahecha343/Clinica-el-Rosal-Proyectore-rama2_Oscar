package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.sena.Clinica.el.Rosal.Service.CitaMedicaService;
import co.edu.sena.Clinica.el.Rosal.dto.CitaMedicaDTO;

@RestController
@RequestMapping("/cita")
@CrossOrigin(origins = "*") // Habilita peticiones desde cualquier origen (ideal para frontend)
public class CitaMedicaController {

    @Autowired
    private CitaMedicaService service;

    // Obtener todas las citas médicas
    @GetMapping("/paciente/{id}")
    public List<CitaMedicaDTO> getByPaciente(@PathVariable Long id) {
        return service.getAll().stream()
                .filter(cita -> cita.getIdPaciente().equals(id))
                .toList();
    }

    // Guardar una nueva cita médica
    @PostMapping
    public void save(@RequestBody CitaMedicaDTO dto) {
        service.save(dto);
    }

    // Eliminar una cita médica por su ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}