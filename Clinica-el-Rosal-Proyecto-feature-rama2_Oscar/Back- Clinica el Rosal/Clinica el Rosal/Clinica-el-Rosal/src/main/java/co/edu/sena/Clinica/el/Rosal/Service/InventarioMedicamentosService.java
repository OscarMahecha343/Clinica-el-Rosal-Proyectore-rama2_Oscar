package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.edu.sena.Clinica.el.Rosal.Entity.InventarioMedicamentosEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.InventarioMedicamentosRepository;
import co.edu.sena.Clinica.el.Rosal.dto.InventarioMedicamentosDTO;

@Service
public class InventarioMedicamentosService {

    @Autowired
    private InventarioMedicamentosRepository repository;

    @RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}

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
    System.out.println("🩺 eliminando medicamento con id: " + id);

    if (!repository.existsById(id)) {
        throw new RuntimeException("No existe el medicamento con id: " + id);
    }
    repository.deleteById(id);

    System.out.println("✅ medicamento eliminado con id: " + id);
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

    public void inactivar(Long id) {
    var medicamento = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("No existe el medicamento con id: " + id));
    medicamento.setEstado("Inactivo");
    repository.save(medicamento);
}

}