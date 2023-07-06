package ar.edu.unt.frc.tup.lciii.proyectoBasico.repositories.jpa;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.entities.MatchEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchJpaRepository extends JpaRepository<MatchEntity, Long>  {

    @Query("SELECT m FROM MatchEntity m WHERE m.player.id = :playerId")
    Optional<List<MatchEntity>> getAllByPlayerId(Long playerId);
}
