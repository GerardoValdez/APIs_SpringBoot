package ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNameIdentity extends Identity{
    @Schema(title = "Username",
            description ="The player username" ,
            example = "myUsername",
            nullable = false)
    @NotNull(message = "user_name can't by null")
    @JsonProperty("user_name")
    private String userName;
}
