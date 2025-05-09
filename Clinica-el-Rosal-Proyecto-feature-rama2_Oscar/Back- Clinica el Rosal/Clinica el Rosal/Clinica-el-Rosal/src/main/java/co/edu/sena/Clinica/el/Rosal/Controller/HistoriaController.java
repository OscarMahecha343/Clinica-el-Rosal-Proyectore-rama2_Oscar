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
@RequestMapping("/historias")
@CrossOrigin(origins = "*")  // <- Agregado para que el frontend pueda acceder sin problemas CORS
public class HistoriaController {

    @Autowired
    private HistoriaService service;

    // Obtener todas las historias clínicas
    @GetMapping
    public List<HistoriaDTO> getAll() {
        return service.getAll();
    }

    // Crear una nueva historia
    @PostMapping
    public void save(@RequestBody HistoriaDTO dto) {
        service.save(dto);
    }

    // Actualizar historia existente
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody HistoriaDTO dto) {
        service.update(id, dto);
    }

    // Eliminar historia por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
