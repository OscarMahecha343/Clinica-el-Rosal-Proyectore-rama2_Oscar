package co.edu.sena.Clinica.el.Rosal.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.Clinica.el.Rosal.Service.UsuarioService;
import co.edu.sena.Clinica.el.Rosal.dto.LoginRequesDTO;
import co.edu.sena.Clinica.el.Rosal.dto.LoginResponseDTO;
import co.edu.sena.Clinica.el.Rosal.dto.ServerResponseDataDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioService service;

    @PostMapping()
    public ServerResponseDataDto<LoginResponseDTO> login(@RequestBody LoginRequesDTO request) {

    try{
         Thread.sleep(10000);


    } catch (InterruptedException e){
        throw new RuntimeException(e);
    };
       

    LoginResponseDTO responseDTO = this.service.login(request);

    return ServerResponseDataDto.<LoginResponseDTO>builder()
            .message("Success")
            .data(responseDTO)
            .status(200)
            .build();
}
    
    
    

}
