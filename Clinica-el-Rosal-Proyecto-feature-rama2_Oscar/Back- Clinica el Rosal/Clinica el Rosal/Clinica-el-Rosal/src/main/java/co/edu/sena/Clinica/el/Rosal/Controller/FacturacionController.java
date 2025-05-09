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

import co.edu.sena.Clinica.el.Rosal.Service.FacturacionService;
import co.edu.sena.Clinica.el.Rosal.dto.FacturacionDTO;

@RestController
@RequestMapping("/facturaciones")
@CrossOrigin(origins = "*") // Habilita acceso desde cualquier frontend
public class FacturacionController {

    @Autowired
    private FacturacionService service;

    // GET: Obtener todas las facturaciones
    @GetMapping
    public List<FacturacionDTO> getAll() {
        return service.getAll();
    }

    // GET: Obtener facturación por ID
    @GetMapping("/{id}")
    public FacturacionDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // POST: Crear nueva facturación
    @PostMapping
    public void save(@RequestBody FacturacionDTO dto) {
        service.save(dto);
    }

    // PUT: Actualizar facturación existente
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody FacturacionDTO dto) {
        dto.setId(id); // Se asegura de usar el ID del path
        service.save(dto);
    }

    // DELETE: Eliminar facturación por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}