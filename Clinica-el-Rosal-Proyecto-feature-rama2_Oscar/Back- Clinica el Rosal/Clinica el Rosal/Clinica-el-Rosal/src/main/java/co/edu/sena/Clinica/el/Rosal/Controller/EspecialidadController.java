package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.sena.Clinica.el.Rosal.Service.EspecialidadService;
import co.edu.sena.Clinica.el.Rosal.dto.EspecialidadDTO;

@RestController
@RequestMapping("/especialidad") // Ruta base para los endpoints de especialidad
@CrossOrigin(origins = "*") // Permitir solicitudes desde cualquier origen
public class EspecialidadController {

    @Autowired
    private EspecialidadService service;

    // GET: Obtener todas las especialidades
    @GetMapping
    public List<EspecialidadDTO> getAll() {
        return service.getAll();
    }

    // GET: Obtener especialidad por ID
    @GetMapping("/{id}")
    public EspecialidadDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // POST: Crear nueva especialidad
    @PostMapping
    public void save(@RequestBody EspecialidadDTO dto) {
        service.save(dto);
    }

    // PUT: Actualizar especialidad existente
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody EspecialidadDTO dto) {
        dto.setId(id); // Asegurar que el ID sea el correcto
        service.save(dto);
    }

    // DELETE: Eliminar especialidad
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
