package ar.edu.unt.frc.tup.lciii.proyectoBasico.services.impl;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.entities.GameEntity;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Game;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.repositories.jpa.GameJpaRepository;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.services.GameServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImplement implements GameServiceInterface {

    @Autowired
    private GameJpaRepository gameJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Game getGame(Long gameId) {

        GameEntity gameEntity = gameJpaRepository.getReferenceById(gameId);

        return modelMapper.map(gameEntity,Game.class);

    }
}
