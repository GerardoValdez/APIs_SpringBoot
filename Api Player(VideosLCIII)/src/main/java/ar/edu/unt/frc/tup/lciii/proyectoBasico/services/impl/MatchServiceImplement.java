package ar.edu.unt.frc.tup.lciii.proyectoBasico.services.impl;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.match.MatchDTO;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.entities.MatchEntity;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Game;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Match;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.MatchStatus;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Player;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.rps.MatchRps;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.repositories.jpa.MatchJpaRepository;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.services.GameServiceInterface;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.services.MatchFactory;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.services.MatchServiceInterface;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.services.PlayerServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImplement implements MatchServiceInterface {

    @Autowired
    MatchJpaRepository matchJpaRepository;

    @Autowired
    PlayerServiceInterface playerServiceInterface;

    @Autowired
    GameServiceInterface gameServiceInterface;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Match> getMatchByPlayer(Long playerId) {
        List<Match> matches = new ArrayList<>();

        Optional<List<MatchEntity>> optionalMatchEntity = matchJpaRepository.getAllByPlayerId(playerId);

        if(optionalMatchEntity.isPresent()){

            optionalMatchEntity.get().forEach(
                    me -> { matches.add(modelMapper.map(me,MatchFactory.createMatch(me.getGame().getCode()).getClass()));} // itera,mapea y agrega a la list de matches
            );

        }

        return matches;
    }

    @Override
    public Match createdMatch(MatchDTO matchDTO) {

        Player player = playerServiceInterface.getPlayerById(matchDTO.getPlayerId());
        Game game = gameServiceInterface.getGame(matchDTO.getGameId());

        Match match = MatchFactory.createMatch(game.getCode());
        match.setPlayer(player);
        match.setGame(game);
        match.setCreatedDate(LocalDateTime.now());
        match.setStatus(MatchStatus.STARTED);

        MatchEntity matchEntity = matchJpaRepository.save(modelMapper.map(match,MatchEntity.class));

        return modelMapper.map(matchEntity, Match.class);

    }
}
