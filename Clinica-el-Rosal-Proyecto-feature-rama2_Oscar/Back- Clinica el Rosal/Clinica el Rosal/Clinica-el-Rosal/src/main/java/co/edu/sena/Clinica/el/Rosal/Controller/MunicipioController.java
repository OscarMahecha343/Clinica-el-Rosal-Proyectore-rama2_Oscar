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

import co.edu.sena.Clinica.el.Rosal.Service.MunicipioService;
import co.edu.sena.Clinica.el.Rosal.dto.MunicipioDTO;

@RestController
@RequestMapping("/municipio")
@CrossOrigin(origins = "*")  // <- Agregado para que el frontend pueda acceder sin problemas CORS

public class MunicipioController {

    @Autowired
    private MunicipioService service;

    // GET: Obtener todos los municipios
    @GetMapping
    public List<MunicipioDTO> getAll() {
        return service.getAll();
    }

    // POST: Crear nuevo municipio
    @PostMapping
    public void save(@RequestBody MunicipioDTO dto) {
        service.save(dto);
    }

    // PUT: Actualizar municipio por ID
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody MunicipioDTO dto) {
        service.update(id, dto);
    }

    // DELETE: Eliminar municipio por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}