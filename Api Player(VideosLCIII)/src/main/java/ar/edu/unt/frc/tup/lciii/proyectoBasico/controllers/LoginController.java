package ar.edu.unt.frc.tup.lciii.proyectoBasico.controllers;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.common.ErrorApi;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.login.Credential;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.login.CredentialV2;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Player;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.services.LoginServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginServiceInterface loginServiceInterface;

    @Operation(
            summary = "Login a player in the platform.",
            description = "Return the player logger in if the credentials are correct.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Operación exitosa",
                    content = @Content(schema = @Schema(implementation = Player.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "The credentials are invalid",
                    content = @Content(schema = @Schema(implementation = ErrorApi.class))
            ),
            @ApiResponse(
                    responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorApi.class))
            )

    })

    @PostMapping("")
    public ResponseEntity<Player> logginPlayer(@RequestBody @Valid Credential credential){

        return ResponseEntity.ok(loginServiceInterface.login(credential));

    }

    //--------------------------CREDENTIALV2------------------------------------------

    @Operation(
            summary = "Login a player in the platform.",
            description = "Return the player logger in if the credentials are correct.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Operación exitosa",
                    content = @Content(schema = @Schema(implementation = Player.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "The credentials are invalid",
                    content = @Content(schema = @Schema(implementation = ErrorApi.class))
            ),
            @ApiResponse(
                    responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorApi.class))
            )

    })

    @PostMapping("/v2")
    public ResponseEntity<Player> logginPlayer(@RequestBody @Valid CredentialV2 credentialV2){

        return ResponseEntity.ok(loginServiceInterface.loginV2(credentialV2));

    }

}
