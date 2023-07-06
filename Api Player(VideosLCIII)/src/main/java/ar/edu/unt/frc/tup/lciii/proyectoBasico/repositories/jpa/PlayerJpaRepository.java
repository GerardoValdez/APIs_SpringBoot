package ar.edu.unt.frc.tup.lciii.proyectoBasico.repositories.jpa;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.entities.PlayerEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*Esta interfaz nos va a permitir acceder a interfaces que seran consumidas por servicios
con @Repository aclaramos que este componente es un reposiotrio con lo cual cuando se inicie la app
este componet se va a levantar en mi aplicationContext*/

/*Con estas lineas tenemos acceso a los metodos de JpaRepository */
@Repository
public interface PlayerJpaRepository extends JpaRepository<PlayerEntity, Long> {

    Optional<PlayerEntity> findByUserNameOrEmail(String userName,String email);
    Optional<PlayerEntity> findByUserNameAndPassword(String userName,String password);
    Optional<PlayerEntity> findByEmailAndPassword(String email,String password);

    //------------------------- CREDENTIALV2  ---------------------
    @Query("SELECT p FROM PlayerEntity p WHERE (p.userName LIKE :identity OR p.email LIKE :identity)" +
            " AND p.password LIKE :password")
    Optional<PlayerEntity> findByUserNameOrEmailAndPassword(@Param("identity") String identity,@Param("password") String password);

}
