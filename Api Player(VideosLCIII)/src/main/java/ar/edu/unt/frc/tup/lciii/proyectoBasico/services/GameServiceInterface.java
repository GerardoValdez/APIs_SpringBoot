package ar.edu.unt.frc.tup.lciii.proyectoBasico.services;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Game;
import org.springframework.stereotype.Service;

@Service
public interface GameServiceInterface {

    Game getGame(Long gameId);
}
