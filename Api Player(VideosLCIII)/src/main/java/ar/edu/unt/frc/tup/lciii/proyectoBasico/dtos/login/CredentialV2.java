package ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*Version clasica de login*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialV2 {
    @Schema(title = "UserName or Email to logged in",
            description ="The player email" ,
            example = "username or email@email.com",
            nullable = false)

    @NotNull(message = "identity can't by null")
    @JsonProperty("identity")
    private String identity;
    @NotNull(message = "password can't by null")
    private String password;
}
