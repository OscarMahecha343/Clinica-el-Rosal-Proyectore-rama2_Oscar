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

import co.edu.sena.Clinica.el.Rosal.Service.SeguroService;
import co.edu.sena.Clinica.el.Rosal.dto.SeguroDTO;

@RestController
@RequestMapping("/seguros")
public class SeguroController {

    @Autowired
    private SeguroService service;

    @GetMapping
    public List<SeguroDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void save(@RequestBody SeguroDTO dto) {
        service.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
