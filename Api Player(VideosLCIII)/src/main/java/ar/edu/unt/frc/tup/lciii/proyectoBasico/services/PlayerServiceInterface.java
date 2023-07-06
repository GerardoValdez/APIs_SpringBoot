package ar.edu.unt.frc.tup.lciii.proyectoBasico.services;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerServiceInterface {

    Player getPlayerById(Long id);

    Player createPlayer(Player player);

    Player getPlayerByUserNameAndPassword(String username, String password);

    Player getPlayerByEmailAndPassword(String email, String password);

    Player getPlayerByUserNameOrEmailAndPassword(String identity, String password);



}
