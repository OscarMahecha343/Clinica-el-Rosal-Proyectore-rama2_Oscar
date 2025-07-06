package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.sena.Clinica.el.Rosal.Service.InventarioMedicamentosService;
import co.edu.sena.Clinica.el.Rosal.dto.InventarioMedicamentosDTO;

@RestController
@RequestMapping("/inventario_medicamentos")
@CrossOrigin(
    origins = "*",
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
public class InventarioMedicamentosController {

    @Autowired
    private InventarioMedicamentosService service;

    @GetMapping
    public List<InventarioMedicamentosDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void save(@RequestBody InventarioMedicamentosDTO dto) {
        service.save(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody InventarioMedicamentosDTO dto) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}/inactivar")
public void inactivar(@PathVariable Long id) {
    service.inactivar(id);
}
}
