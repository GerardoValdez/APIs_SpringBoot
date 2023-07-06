package ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*1ra version Login que acepta varias formas de autenticar
* Polimorfismo usando Jackoson (Identity) */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credential {

    private Identity identity; //acepta alguna de las calses que extienda de identity

    private String password;
}

