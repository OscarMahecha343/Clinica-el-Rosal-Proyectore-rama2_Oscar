package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.FacturacionEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.FacturacionRepository;
import co.edu.sena.Clinica.el.Rosal.dto.FacturacionDTO;

@Service
public class FacturacionService {

    @Autowired
    private FacturacionRepository repository;

    // Guardar nueva facturación o actualizar existente
    public void save(FacturacionDTO dto) {
        FacturacionEntity entity = FacturacionEntity.builder()
                .id(dto.getId()) // permite usar este método para crear o actualizar
                .idPaciente(dto.getIdPaciente())
                .idServicio(dto.getIdServicio())
                .monto(dto.getMonto())
                .fecha(dto.getFecha())
                .build();

        repository.save(entity);
    }

    // Listar todas las facturaciones
    public List<FacturacionDTO> getAll() {
        return repository.findAll().stream().map(entity ->
            FacturacionDTO.builder()
                .id(entity.getId())
                .idPaciente(entity.getIdPaciente())
                .idServicio(entity.getIdServicio())
                .monto(entity.getMonto())
                .fecha(entity.getFecha())
                .build()
        ).collect(Collectors.toList());
    }

    // Buscar por ID
    public FacturacionDTO getById(Long id) {
        return repository.findById(id).map(entity ->
            FacturacionDTO.builder()
                .id(entity.getId())
                .idPaciente(entity.getIdPaciente())
                .idServicio(entity.getIdServicio())
                .monto(entity.getMonto())
                .fecha(entity.getFecha())
                .build()
        ).orElse(null);
    }

    // Eliminar por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
