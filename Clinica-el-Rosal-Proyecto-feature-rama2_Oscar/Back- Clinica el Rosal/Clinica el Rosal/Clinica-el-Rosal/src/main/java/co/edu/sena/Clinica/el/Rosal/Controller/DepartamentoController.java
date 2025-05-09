package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.Clinica.el.Rosal.Service.DepartamentoService;
import co.edu.sena.Clinica.el.Rosal.dto.DepartamentoDTO;

@RestController
@RequestMapping("/departamentos") // Ruta base del recurso
public class DepartamentoController {

    @Autowired
    private DepartamentoService service;

    // GET: Listar todos los departamentos
    @GetMapping
    public List<DepartamentoDTO> getAll() {
        return service.getAll();
    }

    // POST: Guardar un nuevo departamento
    @PostMapping
    public void save(@RequestBody DepartamentoDTO dto) {
        service.save(dto);
    }

    // DELETE: Eliminar un departamento por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}