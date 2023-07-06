package ar.edu.unt.frc.tup.lciii.proyectoBasico.entities;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.MatchStatus;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Player;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.rps.ShapeHand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plays_rps")
public class PlayRpsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "match_rps_id") //en MatchRpsEntity hay una lista @OneToMany
    @ManyToOne
    private MatchRpsEntity matchRps;

    @Enumerated(EnumType.STRING)
    private ShapeHand shapeHandPlayer1;

    @Enumerated(EnumType.STRING)
    private ShapeHand shapeHandPlayer2;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private PlayerEntity winner;


}
