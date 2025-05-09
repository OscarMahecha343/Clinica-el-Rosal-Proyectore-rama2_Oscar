package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.TipoExamen;
import co.edu.sena.Clinica.el.Rosal.Repository.TipoExamenRepository;

@Service
public class TipoExamenService {

    @Autowired
    private TipoExamenRepository tipoExamenRepository;

    public TipoExamen guardarTipoExamen(TipoExamen tipoExamen) {
        return tipoExamenRepository.save(tipoExamen);
    }

    public List<TipoExamen> listarTiposExamen() {
        return tipoExamenRepository.findAll();
    }

    public TipoExamen obtenerTipoExamenPorId(Long id) {
        return tipoExamenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de examen no encontrado con ID: " + id));
    }

    public void eliminarTipoExamen(Long id) {
        tipoExamenRepository.deleteById(id);
    }
}
