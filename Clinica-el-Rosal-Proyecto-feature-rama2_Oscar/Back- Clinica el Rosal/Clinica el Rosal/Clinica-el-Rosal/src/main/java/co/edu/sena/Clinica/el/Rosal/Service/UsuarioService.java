package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.AuxiliarEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.FarmaceuticoEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.MedicoEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.PacienteEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.RolEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.UsuarioEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.UsuarioRepository;
import co.edu.sena.Clinica.el.Rosal.dto.LoginRequesDTO;
import co.edu.sena.Clinica.el.Rosal.dto.LoginResponseDTO;
import co.edu.sena.Clinica.el.Rosal.dto.UsuarioDTO;

@Service
public class UsuarioService {

    private String TAG = "usuario-service";

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private LogService logService;

    public LoginResponseDTO login(LoginRequesDTO request) {
        Optional<UsuarioEntity> optResponse = repository.findByLoginAndPasswordAndIdRol(
            request.getLogin(),
            request.getPassword(),
            RolEntity.builder().id(request.getIdRol()).build()
        );

        return optResponse.map(entity -> {
            String nombre = null;
            String identificacion = null;
            String tipoIdentificacion = null;

            if (entity.getIdPaciente() != null) {
                nombre = entity.getIdPaciente().getNombrePaci() + " " + entity.getIdPaciente().getApellidoPaci();
                identificacion = entity.getIdPaciente().getIdentificacion();
                tipoIdentificacion = entity.getIdPaciente().getTipoIdentificacion();
            } else if (entity.getIdMedico() != null) {
                nombre = entity.getIdMedico().getNombreMedico() + " " + entity.getIdMedico().getApellidosMedicos();
                tipoIdentificacion = entity.getIdMedico().getLicenciaMedica();
            } else if (entity.getIdAuxiliar() != null) {
                nombre = entity.getIdAuxiliar().getNombreAuxiliar() + " " + entity.getIdAuxiliar().getApellidoAuxiliar();
                tipoIdentificacion = entity.getIdAuxiliar().getIdentificacion();
            } else if (entity.getIdFarmaceutico() != null) {
                nombre = entity.getIdFarmaceutico().getNombreFarmaceuta() + " " + entity.getIdFarmaceutico().getApellidoFarmaceuta();
                tipoIdentificacion = entity.getIdFarmaceutico().getNumeroLicencia();
            }

            return LoginResponseDTO.builder()
                    .id(entity.getId())
                    .username(entity.getLogin())
                    .nombre(nombre)
                    .identificacion(identificacion)
                    .tipoIdentificacion(tipoIdentificacion)
                    .rol(entity.getIdRol().getNombre())
                    .isActive(true)
                    .build();
        }).orElse(LoginResponseDTO.builder().isActive(false).build());
    }

    public List<UsuarioDTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> UsuarioDTO.builder()
                        .id(entity.getId())
                        .login(entity.getLogin())
                        .password(entity.getPassword())
                        .idPaciente(entity.getIdPaciente() != null ? entity.getIdPaciente().getId() : null)
                        .idMedico(entity.getIdMedico() != null ? entity.getIdMedico().getId() : null)
                        .idAuxiliar(entity.getIdAuxiliar() != null ? entity.getIdAuxiliar().getId() : null)
                        .idFarmaceutico(entity.getIdFarmaceutico() != null ? entity.getIdFarmaceutico().getId() : null)
                        .idRol(entity.getIdRol() != null ? entity.getIdRol().getId() : null)
                        .codigoRestablecimiento(entity.getCodigoRestablecimiento())
                        .expiracionCodigo(entity.getExpiracionCodigo())
                        .ultimaSolicitud(entity.getUltimaSolicitud())
                        .intentosFallidos(entity.getIntentosFallidos())
                        .build())
                .collect(Collectors.toList());

            /*logService.save(LogRequestDTO.builder()
                        .referencia(TAG + "::login")
                        .data("login::" + request.getUsername()+"pwd::"+request.getPassword+"dni::"+entity.getUsario().getIdRol())
                        .id_usuario(renponse.getId)
                        .estado("SUCCESS"));*/
    }

    public void save(UsuarioDTO dto) {
    UsuarioEntity entity = UsuarioEntity.builder()
            .id(dto.getId())
            .login(dto.getLogin())
            .password(dto.getPassword())
            .idPaciente(dto.getIdPaciente() != null ? PacienteEntity.builder().id(dto.getIdPaciente()).build() : null)
            .idMedico(dto.getIdMedico() != null ? MedicoEntity.builder().id(dto.getIdMedico()).build() : null)
            .idAuxiliar(dto.getIdAuxiliar() != null ? AuxiliarEntity.builder().id(dto.getIdAuxiliar()).build() : null)
            .idFarmaceutico(dto.getIdFarmaceutico() != null ? FarmaceuticoEntity.builder().id(dto.getIdFarmaceutico()).build() : null)
            .idRol(dto.getIdRol() != null ? RolEntity.builder().id(dto.getIdRol()).build() : null)
            .codigoRestablecimiento(dto.getCodigoRestablecimiento())
            .expiracionCodigo(dto.getExpiracionCodigo())
            .ultimaSolicitud(dto.getUltimaSolicitud())
            .intentosFallidos(dto.getIntentosFallidos())
            .build();

    repository.save(entity);
}

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
