package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.Clinica.el.Rosal.Entity.TipoExamen;
import co.edu.sena.Clinica.el.Rosal.Service.TipoExamenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/tipo-examen")
@CrossOrigin(origins = "*")
public class TipoExamenController {

    @Autowired
    private TipoExamenService tipoExamenService;

    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
      
        
        return entity;
    }
    
    public TipoExamen crearTipoExamen(@RequestBody TipoExamen tipoExamen) {
        return tipoExamenService.guardarTipoExamen(tipoExamen);
    }

    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    public List<TipoExamen> listarTipos() {
        return tipoExamenService.listarTiposExamen();
    }

    @GetMapping("/{id}")
    public TipoExamen obtenerTipo(@PathVariable Long id) {
        return tipoExamenService.obtenerTipoExamenPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarTipo(@PathVariable Long id) {
        tipoExamenService.eliminarTipoExamen(id);
    }
}