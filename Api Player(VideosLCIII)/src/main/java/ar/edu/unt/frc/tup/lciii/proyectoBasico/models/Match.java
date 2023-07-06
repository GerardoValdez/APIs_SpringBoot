package ar.edu.unt.frc.tup.lciii.proyectoBasico.models;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.rps.MatchRps;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


//Tipo de subjuegos que se van a tener, deduce en base a la clase, no tengo un capo para indicarle:
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MatchRps.class),
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Match { //la volvimos abstracta para que al crear un Match si o si se debe indicar de que tipo
    private Long id;
    private Game game;
    private Player player;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime createdDate;
    private MatchStatus status;
}
