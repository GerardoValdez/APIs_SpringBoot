package ar.edu.utn.frc.tup.lciii.h2api.Services;

import ar.edu.utn.frc.tup.lciii.h2api.Models.Usuario;

import java.util.Optional;

public interface UserService {
    Usuario guardarUsuario(Usuario usuario); //devuelve un usuario
    Optional<Usuario> obtenerUsuario(Long idUsuario); // metodo para buscar usuario el optional evita
                                                    // error nullpointer exception
    Usuario usuarioAModificar(Long idUsuario, Usuario usuarioModificado);
    boolean eliminarUsuario(Long idUsuario);

}
