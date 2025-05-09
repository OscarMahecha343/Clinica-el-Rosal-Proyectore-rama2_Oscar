package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.Clinica.el.Rosal.Service.PrescripcionMedicaService;
import co.edu.sena.Clinica.el.Rosal.dto.PrescripcionMedicaDTO;

@RestController
@RequestMapping("/prescripciones")
public class PrescripcionMedicaController {

    @Autowired
    private PrescripcionMedicaService service;

    @GetMapping
    public List<PrescripcionMedicaDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void save(@RequestBody PrescripcionMedicaDTO dto) {
        service.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}