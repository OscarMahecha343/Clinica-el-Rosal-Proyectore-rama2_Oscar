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

    // GET: Listar todos los estados de afiliación
    @GetMapping
    public List<EstadoAfiliacionDTO> getAll() {
        return service.getAll();
    }

    // GET: Obtener un estado de afiliación por su ID
    @GetMapping("/{id}")
    public EstadoAfiliacionDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // POST: Crear un nuevo estado de afiliación
    @PostMapping
    public void save(@RequestBody EstadoAfiliacionDTO dto) {
        service.save(dto);
    }

    // PUT: Actualizar estado de afiliación existente
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody EstadoAfiliacionDTO dto) {
        dto.setId(id); // Asegura que el ID usado es el de la ruta
        service.save(dto);
    }

    // DELETE: Eliminar estado de afiliación por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
