package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.sena.Clinica.el.Rosal.Entity.InventarioMedicamentosEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.InventarioMedicamentosRepository;
import co.edu.sena.Clinica.el.Rosal.dto.InventarioMedicamentosDTO;

@Service
public class InventarioMedicamentosService {

    @Autowired
    private InventarioMedicamentosRepository repository;

    public List<InventarioMedicamentosDTO> getAll() {
        return repository.findAll().stream().map(entity -> {
            InventarioMedicamentosDTO dto = new InventarioMedicamentosDTO();
            dto.setId(entity.getId());
            dto.setNombre(entity.getNombre());
            dto.setCantidad(entity.getCantidad());
            dto.setDescripcion(entity.getDescripcion());
            dto.setCategoria(entity.getCategoria());
            dto.setUnidadMedida(entity.getUnidadMedida());
            dto.setPrecioUnitario(entity.getPrecioUnitario());
            dto.setFechaVencimiento(entity.getFechaVencimiento());
            dto.setProveedor(entity.getProveedor());
            dto.setFechaActualizacion(entity.getFechaActualizacion());
            dto.setEstado(entity.getEstado());
            return dto;
        }).collect(Collectors.toList());
    }

    public void save(InventarioMedicamentosDTO dto) {
        InventarioMedicamentosEntity entity = new InventarioMedicamentosEntity();
        entity.setNombre(dto.getNombre());
        entity.setCantidad(dto.getCantidad());
        entity.setDescripcion(dto.getDescripcion());
        entity.setCategoria(dto.getCategoria());
        entity.setUnidadMedida(dto.getUnidadMedida());
        entity.setPrecioUnitario(dto.getPrecioUnitario());
        entity.setFechaVencimiento(dto.getFechaVencimiento());
        entity.setProveedor(dto.getProveedor());
        entity.setFechaActualizacion(dto.getFechaActualizacion());
        entity.setEstado(dto.getEstado());
        repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}