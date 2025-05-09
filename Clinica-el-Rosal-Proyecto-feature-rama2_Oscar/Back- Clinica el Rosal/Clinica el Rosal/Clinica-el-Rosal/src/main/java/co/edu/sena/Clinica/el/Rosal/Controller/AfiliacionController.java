package co.edu.sena.Clinica.el.Rosal.Controller;

import co.edu.sena.Clinica.el.Rosal.Service.AfiliacionService;
import co.edu.sena.Clinica.el.Rosal.dto.AfiliacionDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/afiliaciones")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen, útil para pruebas con Postman o frontend externo
public class AfiliacionController {

    @Autowired
    private AfiliacionService service;

    // Crear o actualizar una afiliación
    @PostMapping
    public ResponseEntity<String> save(@RequestBody AfiliacionDTO dto) {
        service.save(dto);
        return ResponseEntity.ok("Afiliación guardada exitosamente");
    }

    // Obtener todas las afiliaciones
    @GetMapping
    public ResponseEntity<List<AfiliacionDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // Obtener afiliación por ID
    @GetMapping("/{id}")
    public ResponseEntity<AfiliacionDTO> findById(@PathVariable Long id) {
        Optional<AfiliacionDTO> dto = service.findById(id);
        return dto.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar afiliación por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Afiliación eliminada exitosamente");
    }
}
