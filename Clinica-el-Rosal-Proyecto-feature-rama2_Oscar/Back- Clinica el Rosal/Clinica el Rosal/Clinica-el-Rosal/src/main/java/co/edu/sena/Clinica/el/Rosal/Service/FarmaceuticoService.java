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

    // Crear o actualizar un farmacéutico
    public FarmaceuticoDTO save(FarmaceuticoDTO dto) {
        FarmaceuticoEntity entity = FarmaceuticoEntity.builder()
                .id(dto.getId())
                .nombreFarmaceuta(dto.getNombreFarmaceuta())
                .apellidoFarmaceuta(dto.getApellidoFarmaceuta())
                .numeroLicencia(dto.getNumeroLicencia())
                .telefonoFarmaceuta(dto.getTelefonoFarmaceuta())
                .correoFarmaceuta(dto.getCorreoFarmaceuta())
                .direccionFarmaceuta(dto.getDireccionFarmaceuta())
                .build();

        entity = repository.save(entity); // Guardar o actualizar en DB
        dto.setId(entity.getId()); // Actualizar ID generado (si es nuevo)
        return dto;
    }

    // Obtener lista de todos los farmacéuticos
    public List<FarmaceuticoDTO> getAll() {
        return repository.findAll().stream().map(entity ->
            FarmaceuticoDTO.builder()
                .id(entity.getId())
                .nombreFarmaceuta(entity.getNombreFarmaceuta())
                .apellidoFarmaceuta(entity.getApellidoFarmaceuta())
                .numeroLicencia(entity.getNumeroLicencia())
                .telefonoFarmaceuta(entity.getTelefonoFarmaceuta())
                .correoFarmaceuta(entity.getCorreoFarmaceuta())
                .direccionFarmaceuta(entity.getDireccionFarmaceuta())
                .build()
        ).collect(Collectors.toList());
    }

    // Obtener un farmacéutico por su ID
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
                    .direccionFarmaceuta(entity.getDireccionFarmaceuta())
                    .build();
        } else {
            throw new RuntimeException("Farmacéutico no encontrado con ID: " + id);
        }
    }

    // Eliminar un farmacéutico por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

