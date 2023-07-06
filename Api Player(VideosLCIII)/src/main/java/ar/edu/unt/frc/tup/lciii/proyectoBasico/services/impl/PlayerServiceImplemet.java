package ar.edu.unt.frc.tup.lciii.proyectoBasico.services.impl;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.entities.PlayerEntity;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Match;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Player;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.repositories.jpa.PlayerJpaRepository;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.services.MatchServiceInterface;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.services.PlayerServiceInterface;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Table;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImplemet implements PlayerServiceInterface {

    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Player getPlayerById(Long id) {
        Optional<PlayerEntity> optionalPlayerEntity = playerJpaRepository.findById(id);
        if(optionalPlayerEntity.isPresent()){
            PlayerEntity playerEntity = optionalPlayerEntity.get();
            Player player= modelMapper.map(playerEntity,Player.class);
            return player;
        }
        else{
            throw new EntityNotFoundException();
        }

    }

    @Override
    public Player createPlayer(Player player) {

        Optional<PlayerEntity> optionalPlayerEntity =  playerJpaRepository.findByUserNameOrEmail(player.getUserName(),player.getEmail());

        if(optionalPlayerEntity.isEmpty()){
            PlayerEntity createdPlayer = modelMapper.map(player, PlayerEntity.class);
            PlayerEntity savedPlayer =  playerJpaRepository.save(createdPlayer);
            return modelMapper.map(savedPlayer,Player.class);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Player getPlayerByUserNameAndPassword(String userName, String password) {
        Optional<PlayerEntity> optionalPlayerEntity = playerJpaRepository.findByUserNameAndPassword(userName,password);
        if(optionalPlayerEntity.isPresent()){
            PlayerEntity playerEntity = optionalPlayerEntity.get();
            Player player= modelMapper.map(playerEntity,Player.class);
            return player;
        }
        else{
            throw new EntityNotFoundException("Username or password invalid!");
        }
    }

    @Override
    public Player getPlayerByEmailAndPassword(String email, String password) {
        Optional<PlayerEntity> optionalPlayerEntity = playerJpaRepository.findByEmailAndPassword(email,password);
        if(optionalPlayerEntity.isPresent()){
            PlayerEntity playerEntity = optionalPlayerEntity.get();
            Player player= modelMapper.map(playerEntity,Player.class);
            return player;
        }
        else{
            throw new EntityNotFoundException("Email or password invalid!");
        }
    }

    @Override
    public Player getPlayerByUserNameOrEmailAndPassword(String identity, String password) {
        Optional<PlayerEntity> optionalPlayerEntity = playerJpaRepository.findByUserNameOrEmailAndPassword(identity,password);
        if(optionalPlayerEntity.isPresent()){
            PlayerEntity playerEntity = optionalPlayerEntity.get();
            Player player= modelMapper.map(playerEntity,Player.class);
            return player;
        }
        else{
            throw new EntityNotFoundException("Some parameters are incorrect!");
        }

    }


}
