package ar.edu.unt.frc.tup.lciii.proyectoBasico.controllers;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.common.ErrorApi;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.match.MatchDTO;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Match;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.services.MatchServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchServiceInterface matchServiceInterface;

//    @Operation(
//            summary = "Login a player in the platform.",
//            description = "Return the player logger in if the credentials are correct.")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Operación exitosa",
//                    content = @Content(schema = @Schema(implementation = Player.class))
//            ),
//            @ApiResponse(
//                    responseCode = "404", description = "The credentials are invalid",
//                    content = @Content(schema = @Schema(implementation = ErrorApi.class))
//            ),
//            @ApiResponse(
//                    responseCode = "500", description = "Internal Server Error",
//                    content = @Content(schema = @Schema(implementation = ErrorApi.class))
//            )
//
//    })

    @PostMapping("")
    public ResponseEntity<Match> saveMatch(@RequestBody @Valid MatchDTO matchDTO){
        Match matchSaved = matchServiceInterface.createdMatch(matchDTO);

        if(Objects.isNull(matchSaved))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request as an error");
        else
            return ResponseEntity.status(HttpStatus.OK).body(matchSaved);
    }
}
