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

import co.edu.sena.Clinica.el.Rosal.Service.EstadoAfiliacionService;
import co.edu.sena.Clinica.el.Rosal.dto.EstadoAfiliacionDTO;

@RestController
@RequestMapping("/estado-afiliacion")
@CrossOrigin(origins = "*") // Permite acceso desde frontend (ej. HTML o JS)
public class EstadoAfiliacionController {

    @Autowired
    private EstadoAfiliacionService service;

    // GET: Listar todos los estados
    @GetMapping
    public List<EstadoAfiliacionDTO> getAll() {
        return service.getAll();
    }

    // GET: Obtener un estado por su ID
    @GetMapping("/{id}")
    public EstadoAfiliacionDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // POST: Crear nuevo estado
    @PostMapping
    public void save(@RequestBody EstadoAfiliacionDTO dto) {
        service.save(dto);
    }

    // PUT: Actualizar estado existente
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody EstadoAfiliacionDTO dto) {
        dto.setId(id); // Asegura que el ID del path se use en la actualización
        service.save(dto);
    }

    // DELETE: Eliminar por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
