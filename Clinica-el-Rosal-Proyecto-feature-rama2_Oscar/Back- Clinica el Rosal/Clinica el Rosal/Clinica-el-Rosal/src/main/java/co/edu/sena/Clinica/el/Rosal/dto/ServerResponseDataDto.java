package co.edu.sena.Clinica.el.Rosal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ServerResponseDataDto<T> {

    private String message;  // Mensaje al cliente
    private int status;      // Código de estado HTTP
    private T data;          // Cualquier objeto o lista de objetos que se desee retornar

}
