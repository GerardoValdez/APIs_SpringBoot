package ar.edu.unt.frc.tup.lciii.proyectoBasico.services;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.login.Credential;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.login.CredentialV2;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Player;
import org.springframework.stereotype.Service;

@Service
public interface LoginServiceInterface {

    Player login(Credential credential);
    Player loginV2(CredentialV2 credentialV2);
}
