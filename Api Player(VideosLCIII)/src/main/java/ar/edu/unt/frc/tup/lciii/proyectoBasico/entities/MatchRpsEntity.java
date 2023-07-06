package ar.edu.unt.frc.tup.lciii.proyectoBasico.entities;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.MatchStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "matches_rps")
public class MatchRpsEntity extends MatchEntity{
    private Long id; //este campo es una referencia del id del match que esta guardado en MatchEntity
    private Integer numberOfPlays; //numero de veces que se va a jugar en la partida
    private Integer remainderPlays; // cuantas partidas quedan por jugar
    private Integer player1Score;
    private Integer player2Score;

    @OneToMany(mappedBy = "matchRps") //en PlayRpsEntity hay un @ManyToOne con el nombre "matchRps"
    private List<PlayRpsEntity> plays; //lista de jugadas que tiene la partida

    @ManyToOne
    @JoinColumn(name = "winner_id") // el ganador de la partida
    private PlayerEntity winner;



}
