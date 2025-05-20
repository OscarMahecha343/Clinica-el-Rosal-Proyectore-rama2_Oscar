package co.edu.sena.Clinica.el.Rosal.Controller;

import co.edu.sena.Clinica.el.Rosal.Service.AfiliacionService;
import co.edu.sena.Clinica.el.Rosal.dto.AfiliacionDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/afiliacion")
@CrossOrigin(origins = "*")
public class AfiliacionController {

    @Autowired
    private AfiliacionService service;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody AfiliacionDTO dto) {
        service.save(dto);
        return ResponseEntity.ok("Afiliación guardada exitosamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody AfiliacionDTO dto) {
        dto.setId(id);
        service.save(dto);
        return ResponseEntity.ok("Afiliación actualizada exitosamente");
    }

    @GetMapping
    public ResponseEntity<List<AfiliacionDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AfiliacionDTO> findById(@PathVariable Long id) {
        Optional<AfiliacionDTO> dto = service.findById(id);
        return dto.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Afiliación eliminada exitosamente");
    }
}