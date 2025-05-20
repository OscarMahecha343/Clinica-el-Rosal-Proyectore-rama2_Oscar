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

import co.edu.sena.Clinica.el.Rosal.Service.SeguroService;
import co.edu.sena.Clinica.el.Rosal.dto.SeguroDTO;

@RestController
@RequestMapping("/seguro")
@CrossOrigin(origins = "*") // Permitir acceso desde frontend externo
public class SeguroController {

    @Autowired
    private SeguroService service;

    // Obtener todos los seguros
    @GetMapping
    public List<SeguroDTO> getAll() {
        return service.getAll();
    }

    // Crear un nuevo seguro
    @PostMapping
    public void save(@RequestBody SeguroDTO dto) {
        service.save(dto);
    }

    // Actualizar un seguro existente
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody SeguroDTO dto) {
        service.update(id, dto);
    }

    // Eliminar seguro por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
