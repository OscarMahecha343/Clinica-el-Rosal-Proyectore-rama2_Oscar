package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.Clinica.el.Rosal.Service.PacienteService;
import co.edu.sena.Clinica.el.Rosal.dto.PacienteDTO;

@RestController
@RequestMapping("/paciente")
@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen (útil para frontend local)
public class PacienteController {

    @Autowired

    private PacienteService pacienteService;

    // GET: Obtener todos los pacientes
    @GetMapping
    public List<PacienteDTO> getAll() {
        return pacienteService.getAll();
    }

    @GetMapping("/identificacion/{identificacion}")
    public ResponseEntity<PacienteDTO> getByIdentificacion(@PathVariable String identificacion) {
        return ResponseEntity.ok(pacienteService.getByIdentificacion(identificacion));
    }

    // POST: Crear nuevo paciente
    @PostMapping
    public ResponseEntity<?> save(@RequestBody PacienteDTO dto) {
    try {
        PacienteDTO saved = pacienteService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
    }


    @PutMapping("/identificacion/{identificacion}")
public ResponseEntity<?> updateByIdentificacion(@PathVariable String identificacion, @RequestBody PacienteDTO dto) {
    try {
        PacienteDTO actualizado = pacienteService.updateByIdentificacion(identificacion, dto);
        return ResponseEntity.ok(actualizado);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}

    // DELETE: Eliminar paciente por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pacienteService.delete(id);
    }
}
