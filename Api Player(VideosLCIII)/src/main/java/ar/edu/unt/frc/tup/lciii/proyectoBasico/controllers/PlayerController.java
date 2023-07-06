package ar.edu.unt.frc.tup.lciii.proyectoBasico.controllers;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.common.ErrorApi;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.match.MatchDTO;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Match;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Player;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.rps.MatchRps;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.services.MatchServiceInterface;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.services.PlayerServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;


/*En este controller expongo los metodos para las diferentes acciones*/

/*La anotación @RequestMapping se coloca encima de un método o de una clase para
        indicar qué URL o patrón de URL debe asociarse con ese método o clase.*/
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerServiceInterface playerServiceInterface;

    @Autowired
    private MatchServiceInterface matchServiceInterface;



    @Operation(
            summary = "Obtiene un jugador por su ID",
            description = "Devuelve un jugador basado en el ID proporcionado."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación exitosa",
                    content = @Content(schema = @Schema(implementation = Player.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Jugador no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorApi.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(playerServiceInterface.getPlayerById(id));
        }
       finally {

        }
    }





    @Operation(
            summary = "Crea un nuevo jugador",
            description = "Crea un nuevo jugador con la información proporcionada en el cuerpo de la solicitud."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación exitosa",
                    content = @Content(schema = @Schema(implementation = Player.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud inválida",
                    content = @Content(schema = @Schema(implementation = ErrorApi.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(schema = @Schema(implementation = ErrorApi.class))
            )
    })
    /*@Valid valida las reglas que le di a los atributos del Modelo de Player*/
    /*como capturo un excepcion debo agregar en el handler par que no sea tratada como generica*/

    @PostMapping("") //No hace falta una ruta especifica
    public ResponseEntity<Player> createdPlayer(@RequestBody @Valid  Player player){
        try {
            Player savedPlayer = playerServiceInterface.createPlayer(player);
            if (Objects.isNull(savedPlayer)) // savedPlater == null
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El jugador ya esta cargado");
            else
                return ResponseEntity.status(HttpStatus.OK).body(savedPlayer);
        } finally {

        }
    }


    @GetMapping("/{id}/matches")
    public ResponseEntity<List<Match>> getMatchesOfPlayer(@PathVariable Long id){

        List<Match> matches = matchServiceInterface.getMatchByPlayer(id);
        return ResponseEntity.ok(matches);

    }



}
