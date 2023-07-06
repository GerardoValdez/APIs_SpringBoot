package ar.edu.unt.frc.tup.lciii.proyectoBasico;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.controllers.HealthAppController;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.controllers.PlayerController;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.services.PlayerServiceInterface;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*Verifica el contexto levanta todos los beans necesarios para la app*/
@SpringBootTest
public class SmokeTest {

    @Autowired
    private PlayerController playerController;
    @Autowired
    private HealthAppController healthAppController;
    @Autowired
    private PlayerServiceInterface playerServiceInterface;

    @Test
    public void contextLoads() throws Exception {
        assertThat(playerController).isNotNull();
        assertThat(healthAppController).isNotNull();
        assertThat(playerServiceInterface).isNotNull();

    }
}