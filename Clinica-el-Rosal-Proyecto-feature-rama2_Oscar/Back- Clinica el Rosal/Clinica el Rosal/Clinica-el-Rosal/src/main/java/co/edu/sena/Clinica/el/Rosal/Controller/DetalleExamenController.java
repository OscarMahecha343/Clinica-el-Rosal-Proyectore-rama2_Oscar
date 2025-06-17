package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import co.edu.sena.Clinica.el.Rosal.Service.DetalleExamenService;
import co.edu.sena.Clinica.el.Rosal.dto.DetalleExamenDTO;
import co.edu.sena.Clinica.el.Rosal.dto.TipoExamenDTO;

@RestController
@RequestMapping("/detalle_examenes")
@CrossOrigin(origins = "*") // Permite que el frontend se conecte desde otro origen
public class DetalleExamenController {

    @Autowired
    private DetalleExamenService service;

    @GetMapping("/tipo")
    public List<TipoExamenDTO> getAllTiposExamen() {
        return service.findAllTipoExamenes();
    }

    @GetMapping("/{id}")
    public DetalleExamenDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/paciente/{idPaciente}")
    public List<DetalleExamenDTO> getByPaciente(@PathVariable Long idPaciente) {
        return service.getByPacienteId(idPaciente);
    }

    @PostMapping
    public void save(@RequestBody DetalleExamenDTO dto) {
        service.save(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody DetalleExamenDTO dto) {
        dto.setId(id);
        service.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}