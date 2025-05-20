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

import co.edu.sena.Clinica.el.Rosal.Service.ServicioService;
import co.edu.sena.Clinica.el.Rosal.dto.ServicioDTO;

@RestController
@RequestMapping("/servicio")
@CrossOrigin(origins = "*") // Permite acceso desde frontend externo (si aplica)
public class ServicioController {

    @Autowired
    private ServicioService service;

    // Obtener todos los servicios
    @GetMapping
    public List<ServicioDTO> getAll() {
        return service.getAll();
    }

    // Guardar nuevo servicio
    @PostMapping
    public void save(@RequestBody ServicioDTO dto) {
        service.save(dto);
    }

    // Actualizar servicio existente
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ServicioDTO dto) {
        service.update(id, dto);
    }

    // Eliminar servicio por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}