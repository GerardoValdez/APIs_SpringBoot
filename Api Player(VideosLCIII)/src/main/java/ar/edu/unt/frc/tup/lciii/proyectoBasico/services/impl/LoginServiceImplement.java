package ar.edu.unt.frc.tup.lciii.proyectoBasico.services.impl;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.login.Credential;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.login.CredentialV2;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.login.EmailIdentity;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.login.UserNameIdentity;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Player;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.services.LoginServiceInterface;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.services.PlayerServiceInterface;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.ClosedChannelException;
import java.time.LocalDateTime;

@Service
public class LoginServiceImplement implements LoginServiceInterface {


    /*Usamos el playerservice ya que el conoce lo relacionado al player
    * por lo que creamos dos metodos que nos permitan loguear con username o email
    * en el jpa repository tambien creamos dos metodos que hacen las consultas conrrespondientes*/
    @Autowired
    PlayerServiceInterface playerServiceInterface;

    @Override
    public Player login(Credential credential) {

        if (credential.getIdentity() instanceof UserNameIdentity)
            return loginWithIdentity((UserNameIdentity) credential.getIdentity(), credential.getPassword());
        else
            return loginWithIdentity((EmailIdentity) credential.getIdentity(), credential.getPassword());
    }


    private Player loginWithIdentity(UserNameIdentity userNameIdentity, String password) {
        Player player = playerServiceInterface.getPlayerByUserNameAndPassword(userNameIdentity.getUserName(),password);
        return updateLastLogin(player);
    }

    private Player loginWithIdentity(EmailIdentity emailIdentity, String password) {
        Player player = playerServiceInterface.getPlayerByEmailAndPassword(emailIdentity.getEmail(),password);
        return updateLastLogin(player);
    }


    //-----------------------CREDENTIALV2-----------------------------------------------

    @Override
    public Player loginV2(CredentialV2 credentialV2) {
        Player player = playerServiceInterface.getPlayerByUserNameOrEmailAndPassword(credentialV2.getIdentity(), credentialV2.getPassword());
        return updateLastLogin(player);
    }

    //-----------------------CREDENTIALV2-----------------------------------------------


    private Player updateLastLogin(Player player){
        player.setLastLoginDate(LocalDateTime.now());
        return playerServiceInterface.createPlayer(player);
    }



}