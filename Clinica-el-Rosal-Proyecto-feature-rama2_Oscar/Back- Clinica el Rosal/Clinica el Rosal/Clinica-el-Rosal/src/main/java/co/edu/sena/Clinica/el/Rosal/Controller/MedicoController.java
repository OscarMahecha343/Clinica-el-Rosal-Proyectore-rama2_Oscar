package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;
import java.util.stream.Collectors;

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

import co.edu.sena.Clinica.el.Rosal.Service.MedicoService;
import co.edu.sena.Clinica.el.Rosal.dto.MedicoDTO;

@RestController
@RequestMapping("/medico")
@CrossOrigin(origins = "*")
public class MedicoController {

    @Autowired
    private MedicoService service;

    // GET: Obtener todos los médicos
    @GetMapping
    public List<MedicoDTO> getAll() {
        return service.getAll();
    }

    // GET: Obtener médicos por especialidad
    @GetMapping("/especialidad/{id}")
    public List<MedicoDTO> getByEspecialidad(@PathVariable Long id) {
        return service.getAll().stream()
                .filter(medico -> medico.getIdEspecialidad().equals(id))
                .toList();
    }

    // POST: Guardar nuevo médico
    @PostMapping
    public void save(@RequestBody MedicoDTO dto) {
        service.save(dto);
    }

    // PUT: Actualizar médico existente por ID
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody MedicoDTO dto) {
        service.update(id, dto);
    }

    // DELETE: Eliminar médico por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
