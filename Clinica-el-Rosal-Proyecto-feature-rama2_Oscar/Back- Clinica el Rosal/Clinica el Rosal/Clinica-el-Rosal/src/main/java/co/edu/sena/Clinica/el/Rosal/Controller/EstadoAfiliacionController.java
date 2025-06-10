package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.sena.Clinica.el.Rosal.Service.EstadoAfiliacionService;
import co.edu.sena.Clinica.el.Rosal.dto.EstadoAfiliacionDTO;

@RestController
@RequestMapping("/estado_afiliacion")
@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen (Frontend)
public class EstadoAfiliacionController {

    @Autowired
    private EstadoAfiliacionService service;

    @GetMapping
    public List<EstadoAfiliacionDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EstadoAfiliacionDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/paciente/{id}")
    public EstadoAfiliacionDTO getByPaciente(@PathVariable Long id) {
        return service.getByAfiliacionId(id);
    }

    @PostMapping
    public void save(@RequestBody EstadoAfiliacionDTO dto) {
        service.save(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody EstadoAfiliacionDTO dto) {
        dto.setId(id);
        service.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}