package co.edu.sena.Clinica.el.Rosal.Controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.sena.Clinica.el.Rosal.Service.CitaMedicaService;
import co.edu.sena.Clinica.el.Rosal.dto.CitaMedicaDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cita")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CitaMedicaController {

    @Autowired
    private CitaMedicaService service;

    // Guardar cita con verificación previa
    @PostMapping
    public ResponseEntity<Map<String, String>> crearCita(@RequestBody CitaMedicaDTO dto) {      
    service.save(dto);
    Map<String, String> response = new HashMap<>();
    response.put("mensaje", "Cita guardada exitosamente");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/paciente/{id}")
    public List<CitaMedicaDTO> obtenerCitasPorPaciente(@PathVariable Long id) {
        return service.obtenerCitasPorPaciente(id);
    }

    @GetMapping("/fecha/{fecha}")
    public List<CitaMedicaDTO> obtenerCitasPorFecha(@PathVariable String fecha) {
        return service.obtenerCitasPorFecha(LocalDate.parse(fecha));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public CitaMedicaDTO actualizar(@PathVariable Long id, @RequestBody CitaMedicaDTO dto) {
        return service.update(id, dto);
    }

    @GetMapping("/{id}")
    public CitaMedicaDTO buscarPorId(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/medico/{id}/fecha/{fecha}")
    public List<CitaMedicaDTO> obtenerPorMedicoYFecha(@PathVariable Long id, @PathVariable String fecha) {
        return service.obtenerPorMedicoYFecha(id, fecha);
    }
}
