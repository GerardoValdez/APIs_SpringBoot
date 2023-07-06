package ar.edu.utn.frc.tup.lciii.h2api.Repositories;

import ar.edu.utn.frc.tup.lciii.h2api.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//hace el proceso de metodos que tiene jpa
@Repository
public interface UserRepository extends JpaRepository<Usuario,Long> {
}
