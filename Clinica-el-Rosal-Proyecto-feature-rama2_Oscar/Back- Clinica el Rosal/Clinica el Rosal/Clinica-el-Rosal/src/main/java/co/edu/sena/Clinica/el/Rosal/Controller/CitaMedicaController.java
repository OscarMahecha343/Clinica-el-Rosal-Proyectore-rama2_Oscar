package co.edu.sena.Clinica.el.Rosal.Controller;

import java.time.LocalDate;
import java.util.List;

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

    private final CitaMedicaService service;

    @PostMapping
    public ResponseEntity<CitaMedicaDTO> crearCita(@RequestBody CitaMedicaDTO dto) {
        service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/paciente/{id}")
    public List<CitaMedicaDTO> obtenerCitas(@PathVariable("id") Long idPaciente) {
        return service.obtenerCitasPorPaciente(idPaciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/fecha/{fecha}")
    public List<CitaMedicaDTO> obtenerCitasPorFecha(@PathVariable String fecha) {
        return service.obtenerCitasPorFecha(LocalDate.parse(fecha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaMedicaDTO> actualizar(@PathVariable Long id, @RequestBody CitaMedicaDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.update(id, dto));
    }
}