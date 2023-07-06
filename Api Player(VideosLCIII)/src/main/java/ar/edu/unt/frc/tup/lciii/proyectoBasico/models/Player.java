package ar.edu.unt.frc.tup.lciii.proyectoBasico.models;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.utils.validations.password.ValidPassword;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Schema(title = "ID del Jugador",description ="El ID del jugador" ,example = "1")
    private Long id;

    @Schema(title = "Nombre de jugador",description ="El nombre del jugador" ,example = "MiNombre", nullable = false)
    @NotNull(message = "PlayerName is required.")
    private String userName;

    @Schema(title = "Contraseña de jugador",description ="La contraseña del jugador" ,example = "Password03#", nullable = false)
    @NotNull(message = "Password is required.")
    @ValidPassword
    private String password;

    @Schema(title = "Email de jugador",description ="El Email del jugador" ,example = "email@email.com", nullable = false)
    @NotNull(message = "Email is required.")
    @Email(message = "Email invalid" )
    private String email;

    @Schema(title = "Avatar de jugador",description ="El avatar del jugador" ,example = "www.avatars.com/23", nullable = true)
    private String avatar;

    @Schema(title = "Última conexión de jugador",description ="La ultima conexión del jugador" ,example = "email@email.com", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime lastLoginDate;
}
