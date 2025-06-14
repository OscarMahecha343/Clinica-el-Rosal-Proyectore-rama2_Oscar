package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void save(@RequestBody PacienteDTO dto) {
        pacienteService.save(dto);
    }

    // PUT: Actualizar paciente por ID
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody PacienteDTO dto) {
        pacienteService.update(id, dto);
    }

    // DELETE: Eliminar paciente por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pacienteService.delete(id);
    }
}
