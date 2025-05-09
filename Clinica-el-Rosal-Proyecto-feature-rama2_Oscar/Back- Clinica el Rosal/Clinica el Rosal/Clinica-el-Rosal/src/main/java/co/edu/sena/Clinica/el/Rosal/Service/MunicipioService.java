package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.MunicipioEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.MunicipioRepository;
import co.edu.sena.Clinica.el.Rosal.dto.MunicipioDTO;

@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository repository;

    // Crear nuevo municipio (POST)
    public void save(MunicipioDTO dto) {
        MunicipioEntity entity = new MunicipioEntity();
        entity.setNombreMunicipio(dto.getNombreMunicipio());
        entity.setIdDepartamento(dto.getIdDepartamento());
        entity.setEstado(dto.getEstado());
        repository.save(entity); // Inserta en la base de datos
    }

    // Obtener todos los municipios (GET)
    public List<MunicipioDTO> getAll() {
        List<MunicipioEntity> entities = repository.findAll();
        // Convertimos cada entity a DTO para devolverlo al frontend
        return entities.stream().map(entity -> MunicipioDTO.builder()
                .id(entity.getId())
                .nombreMunicipio(entity.getNombreMunicipio())
                .idDepartamento(entity.getIdDepartamento())
                .estado(entity.getEstado())
                .build()).collect(Collectors.toList());
    }

    // Actualizar municipio por ID (PUT)
    public void update(Long id, MunicipioDTO dto) {
        Optional<MunicipioEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            MunicipioEntity entity = optional.get();
            entity.setNombreMunicipio(dto.getNombreMunicipio());
            entity.setIdDepartamento(dto.getIdDepartamento());
            entity.setEstado(dto.getEstado());
            repository.save(entity); // Actualiza el registro
        }
    }

    // Eliminar municipio por ID (DELETE)
    public void delete(Long id) {
        repository.deleteById(id); // Elimina el municipio con ese ID
    }
}
