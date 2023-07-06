package ar.edu.utn.frc.tup.lciii.h2api.Services;

import ar.edu.utn.frc.tup.lciii.h2api.Models.Usuario;
import ar.edu.utn.frc.tup.lciii.h2api.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service //le indico a Sprint que esta clase va a ser mi servicio, aca esta la logica del negocio
@AllArgsConstructor//lo declaro para que funcione el userRepository
public class ImpUserService implements UserService {

    private final UserRepository repositorioUsuario;
    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return repositorioUsuario.save(usuario); //el repositorioUsuario guarda el usuario recibido por poarametro
    }

    @Override
    public Optional<Usuario> obtenerUsuario(Long idUsuario) {

        return Optional.ofNullable(repositorioUsuario.findById(idUsuario).orElseThrow(() -> {
            throw new RuntimeException();
        }));
    }

    @Override
    public Usuario usuarioAModificar(Long idUsuario, Usuario usuarioModificado) {
        Usuario usuarioBuscado = repositorioUsuario.findById(idUsuario).get(); //al darle get te trae el objeto
        usuarioBuscado.setDireccion(usuarioModificado.getDireccion());
        return repositorioUsuario.save(usuarioBuscado);

    }

    @Override
    public boolean eliminarUsuario(Long idUsuario) {
        try{
            repositorioUsuario.deleteById(idUsuario);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
