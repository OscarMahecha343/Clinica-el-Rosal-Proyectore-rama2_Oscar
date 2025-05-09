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
    private InventarioMedicamentosService inventarioMedicamentosService;

    @PostMapping
    public void save(@RequestBody InventarioMedicamentosDTO inventarioMedicamentosDTO) {
        inventarioMedicamentosService.save(inventarioMedicamentosDTO);
    }

    @GetMapping
    public List<InventarioMedicamentosDTO> getAll() {
        return inventarioMedicamentosService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        inventarioMedicamentosService.delete(id);
    }
}