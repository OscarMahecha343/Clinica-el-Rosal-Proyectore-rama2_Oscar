package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.FarmaceuticoEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.FarmaceuticoRepository;
import co.edu.sena.Clinica.el.Rosal.dto.FarmaceuticoDTO;

@Service
public class FarmaceuticoService {

    @Autowired
    private FarmaceuticoRepository repository;

    // Guardar o actualizar un farmaceuta
    public FarmaceuticoDTO save(FarmaceuticoDTO dto) {
        FarmaceuticoEntity entity = FarmaceuticoEntity.builder()
                .id(dto.getId()) // se puede usar null para nuevo o ID para actualizar
                .nombreFarmaceuta(dto.getNombreFarmaceuta())
                .apellidoFarmaceuta(dto.getApellidoFarmaceuta())
                .numeroLicencia(dto.getNumeroLicencia())
                .telefonoFarmaceuta(dto.getTelefonoFarmaceuta())
                .correoFarmaceuta(dto.getCorreoFarmaceuta())
                .dirreccionFarmaceuta(dto.getDirreccionFarmaceuta())
                .build();

        entity = repository.save(entity); // guarda en la base de datos
        dto.setId(entity.getId()); // asigna el ID generado
        return dto;
    }

    // Obtener todos los farmaceuticos
    public List<FarmaceuticoDTO> getAll() {
        return repository.findAll().stream().map(entity ->
            FarmaceuticoDTO.builder()
                .id(entity.getId())
                .nombreFarmaceuta(entity.getNombreFarmaceuta())
                .apellidoFarmaceuta(entity.getApellidoFarmaceuta())
                .numeroLicencia(entity.getNumeroLicencia())
                .telefonoFarmaceuta(entity.getTelefonoFarmaceuta())
                .correoFarmaceuta(entity.getCorreoFarmaceuta())
                .dirreccionFarmaceuta(entity.getDirreccionFarmaceuta())
                .build()
        ).collect(Collectors.toList());
    }

    // Eliminar por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Buscar por ID
    public FarmaceuticoDTO getById(Long id) {
        Optional<FarmaceuticoEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            FarmaceuticoEntity entity = optional.get();
            return FarmaceuticoDTO.builder()
                    .id(entity.getId())
                    .nombreFarmaceuta(entity.getNombreFarmaceuta())
                    .apellidoFarmaceuta(entity.getApellidoFarmaceuta())
                    .numeroLicencia(entity.getNumeroLicencia())
                    .telefonoFarmaceuta(entity.getTelefonoFarmaceuta())
                    .correoFarmaceuta(entity.getCorreoFarmaceuta())
                    .dirreccionFarmaceuta(entity.getDirreccionFarmaceuta())
                    .build();
        } else {
            throw new RuntimeException("Farmaceutico no encontrado con ID: " + id);
        }
    }
}
