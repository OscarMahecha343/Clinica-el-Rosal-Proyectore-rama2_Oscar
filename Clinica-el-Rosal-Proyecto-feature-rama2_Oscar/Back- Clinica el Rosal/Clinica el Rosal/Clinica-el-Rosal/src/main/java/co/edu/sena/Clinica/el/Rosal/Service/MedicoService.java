package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.MedicoEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.MedicoRepository;
import co.edu.sena.Clinica.el.Rosal.dto.MedicoDTO;
import jakarta.transaction.Transactional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    // Crear nuevo médico
    public void save(MedicoDTO dto) {
        MedicoEntity entity = MedicoEntity.builder()
            .nombreMedico(dto.getNombreMedico())
            .apellidosMedicos(dto.getApellidosMedicos())
            .telefonoDoc(dto.getTelefonoDoc())
            .licenciaMedica(dto.getLicenciaMedica())
            .idEspecialidad(dto.getEspecialidad())
            .correo(dto.getCorreo())
            .direccion(dto.getDireccion())
            .consultorio(dto.getConsultorio())
            .build();
        repository.save(entity);
    }

    // Obtener todos los médicos
  public List<MedicoDTO> getByEspecialidad(Long idEspecialidad) {
    return repository.findByIdEspecialidad_Id(idEspecialidad)
        .stream()
        .map(entity -> MedicoDTO.builder()
            .id(entity.getId())
            .nombreMedico(entity.getNombreMedico())
            .apellidosMedicos(entity.getApellidosMedicos())
            .telefonoDoc(entity.getTelefonoDoc())
            .licenciaMedica(entity.getLicenciaMedica())
            .especialidad(entity.getIdEspecialidad())
            .correo(entity.getCorreo())
            .direccion(entity.getDireccion())
            .consultorio(entity.getConsultorio())
            .build())
        .collect(Collectors.toList());
}

    // Actualizar médico
    public void update(Long id, MedicoDTO dto) {
        Optional<MedicoEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            MedicoEntity entity = optional.get();
            entity.setNombreMedico(dto.getNombreMedico());
            entity.setApellidosMedicos(dto.getApellidosMedicos());
            entity.setTelefonoDoc(dto.getTelefonoDoc());
            entity.setLicenciaMedica(dto.getLicenciaMedica());
            entity.setIdEspecialidad(dto.getEspecialidad());
            entity.setCorreo(dto.getCorreo());
            entity.setDireccion(dto.getDireccion());
            entity.setConsultorio(dto.getConsultorio());
            repository.save(entity);
        }
    }

    @Transactional
public List<MedicoDTO> listarMedicosPorEspecialidad(Long idEspecialidad) {
    List<MedicoEntity> medicos = repository.findByIdEspecialidad_Id(idEspecialidad);

    List<MedicoDTO> resultado = new ArrayList<>();
    for (MedicoEntity medico : medicos) {
        MedicoDTO dto = new MedicoDTO();
        dto.setId(medico.getId());
        dto.setNombreMedico(medico.getNombreMedico());
        dto.setApellidosMedicos(medico.getApellidosMedicos());
        dto.setNombreEspecialidad(medico.getIdEspecialidad().getNombreEspecialidad());
        resultado.add(dto);
    }
    return resultado;
}

    // Eliminar médico
    public void delete(Long id) {
        repository.deleteById(id);
    }
}