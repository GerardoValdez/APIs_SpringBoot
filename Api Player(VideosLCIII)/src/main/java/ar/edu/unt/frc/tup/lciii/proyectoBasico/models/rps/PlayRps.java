package ar.edu.unt.frc.tup.lciii.proyectoBasico.models.rps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayRps {
    private Long id;
    private Long matchRpsId;
    private ShapeHand shapeHandPlayer1;
    private ShapeHand shapeHandPlayer2;
    private Long winnerId;
}
