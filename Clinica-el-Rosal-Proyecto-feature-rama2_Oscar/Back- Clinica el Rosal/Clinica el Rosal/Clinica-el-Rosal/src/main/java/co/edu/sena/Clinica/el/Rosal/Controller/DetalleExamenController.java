package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

     @PostMapping("/detalle_examenes/upload")
    public ResponseEntity<?> subirExamen(@ModelAttribute DetalleExamenDTO dto) {
        service.save(dto);
        return ResponseEntity.ok("Examen subido con éxito.");
    }


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

    @PostMapping("/detalle_examenes")
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