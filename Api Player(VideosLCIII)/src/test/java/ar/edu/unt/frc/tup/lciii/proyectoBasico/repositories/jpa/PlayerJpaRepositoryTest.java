package ar.edu.unt.frc.tup.lciii.proyectoBasico.repositories.jpa;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.entities.PlayerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*Test para probar el metodo que creamos con jpaRepositories*/



@DataJpaTest
public class PlayerJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @Test
    public void findByUserNameOrEmailTest(){
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setEmail("email@email.com");
        playerEntity.setUserName("valdez");
        playerEntity.setPassword("Password03#");

        entityManager.persist(playerEntity);
        entityManager.flush();

        Optional<PlayerEntity> result = playerJpaRepository.findByUserNameOrEmail("valdez" ,"email@email.com");
        assertEquals("valdez", result.get().getUserName());
    }
}
