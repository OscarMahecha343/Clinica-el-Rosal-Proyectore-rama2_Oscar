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

    public void save(CitaMedicaDTO dto) {
        PacienteEntity paciente = new PacienteEntity();
        paciente.setId(dto.getIdPaciente());

        MedicoEntity medico = new MedicoEntity();
        medico.setId(dto.getIdMedico());

        EspecialidadEntity especialidad = new EspecialidadEntity();
        especialidad.setId(dto.getIdEspecialidad());

        CitaMedicaEntity entity = CitaMedicaEntity.builder()
                .paciente(paciente)
                .medico(medico)
                .especialidad(especialidad)
                .fecha(dto.getFecha()) // ahora es LocalDate
                .hora(dto.getHora())
                .estado(dto.getEstado())
                .build();

        repository.save(entity);
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
}