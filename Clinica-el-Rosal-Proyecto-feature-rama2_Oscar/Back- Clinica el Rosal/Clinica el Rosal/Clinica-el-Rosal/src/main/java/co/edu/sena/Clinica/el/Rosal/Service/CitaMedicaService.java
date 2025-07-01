package co.edu.sena.Clinica.el.Rosal.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.CitaMedicaEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.EspecialidadEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.MedicoEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.PacienteEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.CitaMedicaRepository;
import co.edu.sena.Clinica.el.Rosal.dto.CitaMedicaDTO;

@Service
public class CitaMedicaService {

    @Autowired
    private CitaMedicaRepository repository;

    @Autowired
private PacienteService pacienteService;

@Autowired
private MedicoService medicoService;

@Autowired
private EspecialidadService especialidadService;

public CitaMedicaDTO save(CitaMedicaDTO dto) {
    PacienteEntity paciente = pacienteService.findById(dto.getIdPaciente());
    MedicoEntity medico = medicoService.findById(dto.getIdMedico());
    EspecialidadEntity especialidad = especialidadService.findById(dto.getIdEspecialidad());

    CitaMedicaEntity entity = CitaMedicaEntity.builder()
            .paciente(paciente)
            .medico(medico)
            .especialidad(especialidad)
            .fecha(dto.getFecha())
            .hora(dto.getHora())
            .estado(dto.getEstado())
            .build();

    CitaMedicaEntity saved = repository.save(entity);
    return convertToDto(saved);
}

    public List<CitaMedicaDTO> obtenerCitasPorPaciente(Long idPaciente) {
        return repository.findByPaciente_Id(idPaciente).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CitaMedicaDTO> obtenerCitasPorFecha(LocalDate fecha) {
        return repository.findByFecha(fecha).stream()
                .map(cita -> CitaMedicaDTO.builder()
                        .hora(cita.getHora())
                        .build())
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public CitaMedicaDTO update(Long id, CitaMedicaDTO dto) {
        CitaMedicaEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));

        copyDtoToEntity(dto, entity);
        return convertToDto(repository.save(entity));
    }

    private void copyDtoToEntity(CitaMedicaDTO dto, CitaMedicaEntity entity) {
        if (dto.getIdPaciente() != null) {
            PacienteEntity paciente = new PacienteEntity();
            paciente.setId(dto.getIdPaciente());
            entity.setPaciente(paciente);
        }

        if (dto.getIdMedico() != null) {
            MedicoEntity medico = new MedicoEntity();
            medico.setId(dto.getIdMedico());
            entity.setMedico(medico);
        }

        if (dto.getIdEspecialidad() != null) {
            EspecialidadEntity especialidad = new EspecialidadEntity();
            especialidad.setId(dto.getIdEspecialidad());
            entity.setEspecialidad(especialidad);
        }

        entity.setFecha(dto.getFecha()); // LocalDate
        entity.setHora(dto.getHora());

        if (dto.getEstado() != null) {
            entity.setEstado(dto.getEstado());
        }
    }

    private CitaMedicaDTO convertToDto(CitaMedicaEntity cita) {
        String nombreMedico = "";
        String nombreConsultorio = "";
        String ubicacionConsultorio = "";

            String nombrePaciente = "";
    if (cita.getPaciente() != null) {
        nombrePaciente = cita.getPaciente().getNombrePaci() + " " + cita.getPaciente().getApellidoPaci();
    }

        if (cita.getMedico() != null) {
            nombreMedico = cita.getMedico().getNombreMedico() + " " + cita.getMedico().getApellidosMedicos();
            if (cita.getMedico().getConsultorio() != null) {
                nombreConsultorio = cita.getMedico().getConsultorio().getNombreConsultorio();
                ubicacionConsultorio = cita.getMedico().getConsultorio().getUbicacion();
            }
        }

        return CitaMedicaDTO.builder()
                .id(cita.getId())
                .idPaciente(cita.getPaciente() != null ? cita.getPaciente().getId() : null)
                .idMedico(cita.getMedico() != null ? cita.getMedico().getId() : null)
                .idEspecialidad(cita.getEspecialidad() != null ? cita.getEspecialidad().getId() : null)
                .fecha(cita.getFecha()) // LocalDate
                .hora(cita.getHora())
                .estado(cita.getEstado())
                .nombreEspecialidad(
                        cita.getEspecialidad() != null ? cita.getEspecialidad().getNombreEspecialidad() : "")
                .nombreMedico(nombreMedico)
                .consultorio(nombreConsultorio)
                .ubicacionConsultorio(ubicacionConsultorio)
                .nombrePaciente(nombrePaciente)
                .build();
    }

    public List<CitaMedicaDTO> obtenerPorMedicoYFecha(Long idMedico, String fecha) {
        LocalDate fechaLocal = LocalDate.parse(fecha);
        return repository.findByMedico_IdAndFecha(idMedico, fechaLocal).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CitaMedicaDTO findById(Long id) {
        CitaMedicaEntity cita = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        return convertToDto(cita);
    }


    public boolean existeCitaEnHorario(Long idMedico, LocalDate fecha, String hora) {
    List<CitaMedicaEntity> citas = repository.findByMedico_IdAndFecha(idMedico, fecha);
    return citas.stream().anyMatch(c -> c.getHora().equals(hora));
}
}