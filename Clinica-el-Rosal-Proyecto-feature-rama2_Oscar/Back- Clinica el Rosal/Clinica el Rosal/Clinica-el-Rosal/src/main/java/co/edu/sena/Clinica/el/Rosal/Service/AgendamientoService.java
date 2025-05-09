package co.edu.sena.Clinica.el.Rosal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.AgendamientoEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.AgendamientoRepository;
import co.edu.sena.Clinica.el.Rosal.dto.AgendamientoDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamientoService {

    @Autowired
    private AgendamientoRepository repository;

    // Guardar un nuevo agendamiento
    public void save(AgendamientoDTO dto) {
        AgendamientoEntity entity = AgendamientoEntity.builder()
                .fechaAgendamiento(dto.getFechaAgendamiento())
                .horaAgendamiento(dto.getHoraAgendamiento())
                .idPaciente(dto.getIdPaciente())
                .idMedico(dto.getIdMedico())
                .idEspecialidad(dto.getIdEspecialidad())
                .sede(dto.getSede())
                .estadoDisponibilidad(dto.getEstadoDisponibilidad())
                .motivoConsulta(dto.getMotivoConsulta())
                .id_Usuario_creador(dto.getId_Usuario_creador())
                .tipoCreador(dto.getTipoCreador())
                .build();

        repository.save(entity);
    }

    // Obtener todos los registros de agendamiento
    public List<AgendamientoDTO> getAll() {
        return repository.findAll().stream().map(entity -> AgendamientoDTO.builder()
                .id(entity.getId())
                .fechaAgendamiento(entity.getFechaAgendamiento())
                .horaAgendamiento(entity.getHoraAgendamiento())
                .idPaciente(entity.getIdPaciente())
                .idMedico(entity.getIdMedico())
                .idEspecialidad(entity.getIdEspecialidad())
                .sede(entity.getSede())
                .estadoDisponibilidad(entity.getEstadoDisponibilidad())
                .motivoConsulta(entity.getMotivoConsulta())
                .id_Usuario_creador(entity.getId_Usuario_creador())
                .tipoCreador(entity.getTipoCreador())
                .build()
        ).collect(Collectors.toList());
    }
}
