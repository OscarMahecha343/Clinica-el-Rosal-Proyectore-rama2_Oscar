package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.sena.Clinica.el.Rosal.Service.FarmaceuticoService;
import co.edu.sena.Clinica.el.Rosal.dto.FarmaceuticoDTO;

@RestController
@RequestMapping("/farmaceutico")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (útil para frontend)
public class FarmaceuticoController {

    @Autowired
    private FarmaceuticoService service;

    // GET: Lista de todos los farmacéuticos
    @GetMapping
    public List<FarmaceuticoDTO> getAll() {
        return service.getAll();
    }

    // GET: Obtener farmacéutico por ID
    @GetMapping("/{id}")
    public FarmaceuticoDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // POST: Crear nuevo farmacéutico
    @PostMapping
    public FarmaceuticoDTO save(@RequestBody FarmaceuticoDTO dto) {
        return service.save(dto);
    }

    // PUT: Actualizar farmacéutico
    @PutMapping("/{id}")
    public FarmaceuticoDTO update(@PathVariable Long id, @RequestBody FarmaceuticoDTO dto) {
        dto.setId(id); // Asegura que se actualice el registro correcto
        return service.save(dto);
    }

    // DELETE: Eliminar farmacéutico por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
