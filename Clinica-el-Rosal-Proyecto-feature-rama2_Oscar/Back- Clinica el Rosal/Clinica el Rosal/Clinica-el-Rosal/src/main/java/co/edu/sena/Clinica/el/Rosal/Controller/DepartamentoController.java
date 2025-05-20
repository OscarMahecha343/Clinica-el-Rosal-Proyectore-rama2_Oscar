package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.sena.Clinica.el.Rosal.Service.DepartamentoService;
import co.edu.sena.Clinica.el.Rosal.dto.DepartamentoDTO;

@RestController
@RequestMapping("/departamento") // Ruta base de los endpoints
public class DepartamentoController {

    @Autowired
    private DepartamentoService service;

    // GET /departamento
    @GetMapping
    public List<DepartamentoDTO> getAll() {
        return service.getAll();
    }

    // GET /departamento/{id}
    @GetMapping("/{id}")
    public Optional<DepartamentoDTO> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // POST /departamento
    @PostMapping
    public void save(@RequestBody DepartamentoDTO dto) {
        service.save(dto);
    }

    // DELETE /departamento/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}