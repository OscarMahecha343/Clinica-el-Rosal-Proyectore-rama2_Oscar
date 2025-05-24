package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.UsuarioEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.UsuarioRepository;
import co.edu.sena.Clinica.el.Rosal.dto.LoginRequesDTO;
import co.edu.sena.Clinica.el.Rosal.dto.LoginResponseDTO;
import co.edu.sena.Clinica.el.Rosal.dto.UsuarioDTO;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public LoginResponseDTO login(LoginRequesDTO request) {

        LoginResponseDTO response;

        Optional<UsuarioEntity> optResponse  = this.repository
            .findByLoginAndPasswordAndIdRol(request.getLogin(), request.getPassword(), request.getIdRol());

        if(optResponse.isPresent()) {
            UsuarioEntity entity = optResponse.get();
            response = LoginResponseDTO.builder()
                .id(entity.getId())
                .rol(entity.getIdRol())
                .isActive(true)
                .build();

        } else {
            response = LoginResponseDTO.builder()
                .isActive(false)
                .build();
        }   

        return response;
    }

    // Método para obtener todos los usuarios y convertirlos en DTO
    public List<UsuarioDTO> getAll() {
        return repository.findAll().stream()
                .map(this::convertToDTO) // Usamos método auxiliar
                .collect(Collectors.toList());
    }

    // Método para guardar un nuevo usuario desde un DTO
    public void save(UsuarioDTO dto) {
        UsuarioEntity entity = convertToEntity(dto); // Convertimos a entidad
        repository.save(entity);
    }

    // Método para eliminar un usuario por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // ------------------ MÉTODOS AUXILIARES ------------------

    // Convertir de Entity a DTO
    private UsuarioDTO convertToDTO(UsuarioEntity entity) {
        return UsuarioDTO.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .password(entity.getPassword())
                .idPaciente(entity.getIdPaciente())
                .idMedico(entity.getIdMedico())
                .idAuxiliar(entity.getIdAuxiliar())
                .idFarmaceutico(entity.getIdFarmaceutico())
                .idRol(entity.getIdRol())
                .codigoRestablecimiento(entity.getCodigoRestablecimiento())
                .expiracionCodigo(entity.getExpiracionCodigo())
                .ultimaSolicitud(entity.getUltimaSolicitud())
                .intentosFallidos(entity.getIntentosFallidos())
                .build();
    }

    // Convertir de DTO a Entity
    private UsuarioEntity convertToEntity(UsuarioDTO dto) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
        entity.setIdPaciente(dto.getIdPaciente());
        entity.setIdMedico(dto.getIdMedico());
        entity.setIdAuxiliar(dto.getIdAuxiliar());
        entity.setIdFarmaceutico(dto.getIdFarmaceutico());
        entity.setIdRol(dto.getIdRol());
        entity.setCodigoRestablecimiento(dto.getCodigoRestablecimiento());
        entity.setExpiracionCodigo(dto.getExpiracionCodigo());
        entity.setUltimaSolicitud(dto.getUltimaSolicitud());
        entity.setIntentosFallidos(dto.getIntentosFallidos());
        return entity;
    }
}