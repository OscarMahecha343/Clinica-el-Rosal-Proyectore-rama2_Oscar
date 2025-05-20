package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.Clinica.el.Rosal.Service.TipoExamenService;
import co.edu.sena.Clinica.el.Rosal.dto.TipoExamenDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/tipo_examen")
@CrossOrigin(origins = "*")

public class TipoExamenController {

    @Autowired
    private TipoExamenService tipoExamenService;

    // Crear o actualizar tipo de examen
    @PostMapping
    public TipoExamenDTO crearTipoExamen(@RequestBody TipoExamenDTO dto) {
        return tipoExamenService.guardarTipoExamen(dto);
    }

    // Listar todos los tipos de examen
    @GetMapping
    public List<TipoExamenDTO> listarTipos() {
        return tipoExamenService.listarTiposExamen();
    }

    // Obtener tipo de examen por ID
    @GetMapping("/{id}")
    public TipoExamenDTO obtenerTipo(@PathVariable Long id) {
        return tipoExamenService.obtenerTipoExamenPorId(id);
    }

    // Eliminar tipo de examen por ID
    @DeleteMapping("/{id}")
    public void eliminarTipo(@PathVariable Long id) {
        tipoExamenService.eliminarTipoExamen(id);
    }
}