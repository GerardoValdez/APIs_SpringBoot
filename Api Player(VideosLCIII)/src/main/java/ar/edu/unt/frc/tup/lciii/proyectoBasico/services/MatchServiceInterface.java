package ar.edu.unt.frc.tup.lciii.proyectoBasico.services;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.match.MatchDTO;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Match;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.rps.MatchRps;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchServiceInterface {

    List<Match> getMatchByPlayer(Long playerId);

    Match createdMatch(MatchDTO matchDTO);
}
