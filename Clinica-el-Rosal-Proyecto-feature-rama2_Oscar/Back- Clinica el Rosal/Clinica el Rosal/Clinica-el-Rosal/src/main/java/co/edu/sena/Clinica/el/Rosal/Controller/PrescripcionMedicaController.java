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

import co.edu.sena.Clinica.el.Rosal.Service.PrescripcionMedicaService;
import co.edu.sena.Clinica.el.Rosal.dto.PrescripcionMedicaDTO;

@RestController
@RequestMapping("/prescripción medica")
@CrossOrigin(origins = "*")
public class PrescripcionMedicaController {

    @Autowired
    private PrescripcionMedicaService service;

    // Obtener todas las prescripciones
    @GetMapping
    public List<PrescripcionMedicaDTO> getAll() {
        return service.getAll();
    }

    // Crear nueva prescripción
    @PostMapping
    public void save(@RequestBody PrescripcionMedicaDTO dto) {
        service.save(dto);
    }

    // Actualizar una prescripción por ID
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody PrescripcionMedicaDTO dto) {
        service.update(id, dto);
    }

    // Eliminar prescripción por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}