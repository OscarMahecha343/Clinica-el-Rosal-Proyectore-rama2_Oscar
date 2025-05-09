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

import co.edu.sena.Clinica.el.Rosal.Service.FarmaceuticoService;
import co.edu.sena.Clinica.el.Rosal.dto.FarmaceuticoDTO;


@RestController
@RequestMapping("/farmaceuticos")
@CrossOrigin(origins = "*") // permite llamadas desde cualquier origen (útil para el frontend)
public class FarmaceuticoController {

    @Autowired
    private FarmaceuticoService service;

    // Obtener todos los farmaceuticos
    @GetMapping
    public List<FarmaceuticoDTO> getAll() {
        return service.getAll();
    }

    // Obtener un farmaceutico por ID
    @GetMapping("/{id}")
    public FarmaceuticoDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // Crear un nuevo farmaceutico
    @PostMapping
    public FarmaceuticoDTO save(@RequestBody FarmaceuticoDTO dto) {
        return service.save(dto);
    }

    // Actualizar un farmaceutico
    @PutMapping("/{id}")
    public FarmaceuticoDTO update(@PathVariable Long id, @RequestBody FarmaceuticoDTO dto) {
        dto.setId(id); // asegura que se actualice el registro correcto
        return service.save(dto);
    }

    // Eliminar un farmaceutico
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}