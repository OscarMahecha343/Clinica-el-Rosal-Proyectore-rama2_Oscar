package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.TipoExamenEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.TipoExamenRepository;
import co.edu.sena.Clinica.el.Rosal.dto.TipoExamenDTO;

@Service
public class TipoExamenService {

    @Autowired
    private TipoExamenRepository repository;

    // Crear o actualizar tipo de examen
    public TipoExamenDTO guardarTipoExamen(TipoExamenDTO dto) {
        TipoExamenEntity entity = TipoExamenEntity.builder()
                .id(dto.getId()) // null para nuevo, con valor para update
                .nombre(dto.getNombre())
                .build();
        TipoExamenEntity guardado = repository.save(entity);
        return TipoExamenDTO.builder()
                .id(guardado.getId())
                .nombre(guardado.getNombre())
                .build();
    }

    // Listar todos los tipos de examen
    public List<TipoExamenDTO> listarTiposExamen() {
        return repository.findAll().stream().map(entity ->
                TipoExamenDTO.builder()
                        .id(entity.getId())
                        .nombre(entity.getNombre())
                        .build()
        ).collect(Collectors.toList());
    }

    // Obtener tipo de examen por ID
    public TipoExamenDTO obtenerTipoExamenPorId(Long id) {
        TipoExamenEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de examen no encontrado con ID: " + id));
        return TipoExamenDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }

    // Eliminar tipo de examen por ID
    public void eliminarTipoExamen(Long id) {
        repository.deleteById(id);
    }
}