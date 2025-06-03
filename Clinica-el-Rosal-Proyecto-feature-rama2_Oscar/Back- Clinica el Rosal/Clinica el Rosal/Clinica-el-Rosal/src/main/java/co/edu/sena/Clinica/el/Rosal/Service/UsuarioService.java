package co.edu.sena.Clinica.el.Rosal.Service;

import java.sql.Date;
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
        String apellido = null;
        String genero = null;
        Date fechaNacimiento = null;
        String tipoIdentificacion = null;
        String identificacion = null;
        Long idSeguro = null;
        String telefono = null;
        String direccion = null;
        String grupoSanguineo = null;
        String alergias = null;
        String tipoDeAlergia = null;
        Long idMunicipio = null;

        if (entity.getIdPaciente() != null) {
            PacienteEntity paciente = entity.getIdPaciente();
            nombre = paciente.getNombrePaci();
            apellido = paciente.getApellidoPaci();
            genero = paciente.getGenero();
            fechaNacimiento = paciente.getFechaNacimiento();
            tipoIdentificacion = paciente.getTipoIdentificacion();
            identificacion = paciente.getIdentificacion();
            telefono = paciente.getTelefono();
            direccion = paciente.getDireccion();
            grupoSanguineo = paciente.getGrupoSangineo();
            alergias = paciente.getAlergias();
            tipoDeAlergia = paciente.getTipoAlergia();
        } else if (entity.getIdMedico() != null) {
            MedicoEntity medico = entity.getIdMedico();
            nombre = medico.getNombreMedico();
            apellido = medico.getApellidosMedicos();
            tipoIdentificacion = medico.getLicenciaMedica();
        } else if (entity.getIdAuxiliar() != null) {
            AuxiliarEntity aux = entity.getIdAuxiliar();
            nombre = aux.getNombreAuxiliar();
            apellido = aux.getApellidoAuxiliar();
            tipoIdentificacion = aux.getIdentificacion();
        } else if (entity.getIdFarmaceutico() != null) {
            FarmaceuticoEntity farm = entity.getIdFarmaceutico();
            nombre = farm.getNombreFarmaceuta();
            apellido = farm.getApellidoFarmaceuta();
            tipoIdentificacion = farm.getNumeroLicencia();
        }

        return LoginResponseDTO.builder()
            .id(entity.getId())
            .username(entity.getLogin())
            .nombre(nombre)
            .apellido(apellido)
            .genero(genero)
            .fechaNacimiento(fechaNacimiento)
            .tipoIdentificacion(tipoIdentificacion)
            .identificacion(identificacion)
            .idSeguro(idSeguro)
            .telefono(telefono)
            .direccion(direccion)
            .grupoSanguineo(grupoSanguineo)
            .alergias(alergias)
            .tipoDeAlergia(tipoDeAlergia)
            .idMunicipio(idMunicipio)
            .rol(entity.getIdRol().getNombre())
            .isActive(true)
            .build();
    }).orElse(LoginResponseDTO.builder().isActive(false).build()); }

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
