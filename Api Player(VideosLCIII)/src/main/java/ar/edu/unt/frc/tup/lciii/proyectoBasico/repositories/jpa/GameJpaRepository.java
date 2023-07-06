package ar.edu.unt.frc.tup.lciii.proyectoBasico.repositories.jpa;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameJpaRepository extends JpaRepository<GameEntity, Long> {

    GameEntity getReferenceById(Long Id);
}
