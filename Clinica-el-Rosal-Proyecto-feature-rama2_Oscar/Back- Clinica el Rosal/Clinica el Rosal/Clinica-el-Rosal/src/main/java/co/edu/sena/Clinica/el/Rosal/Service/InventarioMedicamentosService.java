package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.Optional;
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

    /**
     * Obtener todos los registros del inventario.
     */
    public List<InventarioMedicamentosDTO> getAll() {
        return repository.findAll().stream().map(entity -> InventarioMedicamentosDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .cantidad(entity.getCantidad())
                .descripcion(entity.getDescripcion())
                .categoria(entity.getCategoria())
                .unidadMedida(entity.getUnidadMedida())
                .precioUnitario(entity.getPrecioUnitario())
                .fechaVencimiento(entity.getFechaVencimiento())
                .proveedor(entity.getProveedor())
                .fechaActualizacion(entity.getFechaActualizacion())
                .estado(entity.getEstado())
                .build()
        ).collect(Collectors.toList());
    }

    /**
     * Guardar un nuevo medicamento.
     */
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

    /**
     * Eliminar un medicamento por su ID.
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }

    /**
     * Actualizar un medicamento existente.
     */
    public void update(Long id, InventarioMedicamentosDTO dto) {
        Optional<InventarioMedicamentosEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            InventarioMedicamentosEntity entity = optional.get();
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
    }
}