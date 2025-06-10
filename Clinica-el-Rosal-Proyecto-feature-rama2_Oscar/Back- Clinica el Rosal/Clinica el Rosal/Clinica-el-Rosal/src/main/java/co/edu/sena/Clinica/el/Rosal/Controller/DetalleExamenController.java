package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import co.edu.sena.Clinica.el.Rosal.Service.DetalleExamenService;
import co.edu.sena.Clinica.el.Rosal.dto.DetalleExamenDTO;

@RestController
@RequestMapping("/detalle_examenes")
@CrossOrigin(origins = "*") // Permite que el frontend se conecte desde otro origen
public class DetalleExamenController {

    @Autowired
    private DetalleExamenService service;

    // GET /detalle_examenes/paciente/{id}
    @GetMapping("/paciente/{id}")
    public List<DetalleExamenDTO> getByPacienteId(@PathVariable Long id) {
        return service.getByPacienteId(id);
    }

    // GET /detalle_examenes/{id}
    @GetMapping("/{id}")
    public DetalleExamenDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // POST /detalle_examenes
    @PostMapping
    public void save(@RequestBody DetalleExamenDTO dto) {
        service.save(dto);
    }

    // PUT /detalle_examenes/{id}
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody DetalleExamenDTO dto) {
        dto.setId(id); // Asegura que se actualice el registro correcto
        service.save(dto);
    }

    // DELETE /detalle_examenes/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
