package ar.edu.unt.frc.tup.lciii.proyectoBasico.entities;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.MatchStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/*En la clase MatchEntity, @ManyToOne se utiliza para establecer relaciones
muchos a uno con las entidades GameEntity y PlayerEntity(la partida tiene un solo id jugador y juego)*/

/* @JoinColumn(name="game_id") se indica el nombre de la columna de la tabla actual en la
cual se va a guardar el id del game en este acaso (va en conjunto con ManyToOne).*/

/* @Enumerated(EnumType.STRING) se aplica al campo status para indicar que es un enum
y se almacenará como una cadena de texto en la base de datos.*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "matches")
@Inheritance(strategy = InheritanceType.JOINED)
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="game_id")
    @ManyToOne
    private GameEntity game;

    @JoinColumn(name="player_id")
    @ManyToOne
    private PlayerEntity player;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    @Enumerated(EnumType.STRING)
    private MatchStatus status;
}
