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

import co.edu.sena.Clinica.el.Rosal.Service.EspecialidadService;
import co.edu.sena.Clinica.el.Rosal.dto.EspecialidadDTO;

@RestController
@RequestMapping("/especialidades") // Ruta base para los endpoints
@CrossOrigin(origins = "*") // Permitir solicitudes desde cualquier origen (útil para frontend externo)
public class EspecialidadController {

    @Autowired
    private EspecialidadService service;

    // GET: Listar todas las especialidades
    @GetMapping
    public List<EspecialidadDTO> getAll() {
        return service.getAll();
    }

    // GET: Obtener una especialidad por ID
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
        dto.setId(id); // Asegura que el ID sea el correcto
        service.save(dto);
    }

    // DELETE: Eliminar especialidad por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

