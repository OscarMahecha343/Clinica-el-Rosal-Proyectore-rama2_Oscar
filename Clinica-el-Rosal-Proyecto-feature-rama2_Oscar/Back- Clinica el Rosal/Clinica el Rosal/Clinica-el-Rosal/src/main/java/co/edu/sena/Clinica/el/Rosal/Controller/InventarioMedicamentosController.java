package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.sena.Clinica.el.Rosal.Service.InventarioMedicamentosService;
import co.edu.sena.Clinica.el.Rosal.dto.InventarioMedicamentosDTO;

@RestController
@RequestMapping("/inventario_medicamentos")
@CrossOrigin(origins = "*")  // <- Agregado para que el frontend pueda acceder sin problemas CORS

public class InventarioMedicamentosController {

    @Autowired
    private InventarioMedicamentosService service;

    /**
     * Obtener todos los medicamentos.
     */
    @GetMapping
    public List<InventarioMedicamentosDTO> getAll() {
        return service.getAll();
    }

    /**
     * Guardar un nuevo medicamento.
     */
    @PostMapping
    public void save(@RequestBody InventarioMedicamentosDTO dto) {
        service.save(dto);
    }

    /**
     * Actualizar un medicamento existente por ID.
     */
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody InventarioMedicamentosDTO dto) {
        service.update(id, dto);
    }

    /**
     * Eliminar un medicamento por ID.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}