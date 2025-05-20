package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.sena.Clinica.el.Rosal.Service.FacturacionService;
import co.edu.sena.Clinica.el.Rosal.dto.FacturacionDTO;

/**
 * Controlador REST para la gestión de facturaciones.
 * Expone los endpoints para que el frontend consuma la API.
 */
@RestController
@RequestMapping("/facturacion")
@CrossOrigin(origins = "*") // Permite conexión desde cualquier origen (frontend)
public class FacturacionController {

    @Autowired
    private FacturacionService service;

    /** Obtener todas las facturaciones */
    @GetMapping
    public List<FacturacionDTO> getAll() {
        return service.getAll();
    }

    /** Obtener una facturación por ID */
    @GetMapping("/{id}")
    public FacturacionDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    /** Crear una nueva facturación */
    @PostMapping
    public void save(@RequestBody FacturacionDTO dto) {
        service.save(dto);
    }

    /** Actualizar una facturación existente */
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody FacturacionDTO dto) {
        dto.setId(id); // Se asegura que el ID del path sea el usado
        service.save(dto);
    }

    /** Eliminar una facturación */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}