package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import co.edu.sena.Clinica.el.Rosal.Service.AgendamientoService;
import co.edu.sena.Clinica.el.Rosal.dto.AgendamientoDTO;
import co.edu.sena.Clinica.el.Rosal.dto.ServerResponseDataDto;

@RestController
@RequestMapping("/agendamiento")
@CrossOrigin(origins = "*")
public class AgendamientoController {

    @Autowired
    private AgendamientoService service;

    // Endpoint para registrar agendamiento
    @PostMapping
    public ServerResponseDataDto<?> create(@RequestBody AgendamientoDTO dto) {
        service.save(dto);
        return ServerResponseDataDto.builder()
                .message("Agendamiento registrado exitosamente")
                .status(HttpStatus.CREATED.value())
                .build();
    }

    // Endpoint para consultar todos los agendamientos
    @GetMapping
    public ServerResponseDataDto<List<AgendamientoDTO>> getAll() {
        List<AgendamientoDTO> agendamientos = service.getAll();
        return ServerResponseDataDto.<List<AgendamientoDTO>>builder()
                .data(agendamientos)
                .message("Consulta exitosa")
                .status(HttpStatus.OK.value())
                .build();
    }
}