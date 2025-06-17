package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.Clinica.el.Rosal.Service.UsuarioService;
import co.edu.sena.Clinica.el.Rosal.dto.UsuarioDTO;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen (útil para frontend local)
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/identificacion/{identificacion}")
    public ResponseEntity<UsuarioDTO> obtenerPorIdentificacion(@PathVariable String identificacion) {
        return ResponseEntity.ok(service.findByIdentificacion(identificacion));
    }

    @PostMapping
    public void save(@RequestBody UsuarioDTO dto) {
        service.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}