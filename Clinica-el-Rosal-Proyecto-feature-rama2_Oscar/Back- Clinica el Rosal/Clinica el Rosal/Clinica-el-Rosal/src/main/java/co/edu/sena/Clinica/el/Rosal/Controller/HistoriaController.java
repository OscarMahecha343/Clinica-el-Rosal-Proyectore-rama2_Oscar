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

import co.edu.sena.Clinica.el.Rosal.Service.HistoriaService;
import co.edu.sena.Clinica.el.Rosal.dto.HistoriaDTO;

@RestController
@RequestMapping("/historia")
@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen (frontend)
public class HistoriaController {

    @Autowired
    private HistoriaService service;

    // Obtener todas las historias
    @GetMapping
    public List<HistoriaDTO> getAll() {
        return service.getAll();
    }

    // Obtener una historia por ID
    @GetMapping("/{id}")
    public HistoriaDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // Crear nueva historia
    @PostMapping
    public HistoriaDTO save(@RequestBody HistoriaDTO dto) {
        return service.save(dto);
    }

    // Actualizar historia existente
    @PutMapping("/{id}")
    public HistoriaDTO update(@PathVariable Long id, @RequestBody HistoriaDTO dto) {
        dto.setId(id);
        return service.save(dto); // reutiliza método save
    }

    // Eliminar historia
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}