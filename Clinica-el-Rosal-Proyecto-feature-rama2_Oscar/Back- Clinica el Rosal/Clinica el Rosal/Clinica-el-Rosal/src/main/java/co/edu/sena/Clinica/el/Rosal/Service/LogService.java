package co.edu.sena.Clinica.el.Rosal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.LogEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.LogRepository;
import co.edu.sena.Clinica.el.Rosal.dto.LogRequestDTO;


@Service
public class LogService {

    @Autowired
    private LogRepository repository;

    public void save(LogRequestDTO log) {

        LogEntity entity = new LogEntity();
        entity.setReferencia(log.getReferencia());
        entity.setFecha(log.getFecha());
        entity.setData(log.getData());
        entity.setEstado(log.getEstado());
        entity.setIdUsuario(log.getIdUsuario());

        repository.save(entity);

    }
}
