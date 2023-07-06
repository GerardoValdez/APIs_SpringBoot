package ar.edu.utn.frc.tup.lciii.h2api.Controllers;

import ar.edu.utn.frc.tup.lciii.h2api.Models.Usuario;
import ar.edu.utn.frc.tup.lciii.h2api.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //indica que es un controlador
@RequestMapping("/usuarios") //que ruta debe seguir para llegar al controlador
@RequiredArgsConstructor //es lo mismo que allConstructor
public class UserController { //clase que se expone en las url y consume el servicio

    private final UserService servicioUsuario; //del controlador va al servicio por inyeccion de dep

    @PostMapping //declaramos un guardar  //RequestBody indicacamos que necesitamos un JSON de tipo usuario
    public ResponseEntity guardarUsuario(@RequestBody Usuario usuario){ //clase para manejar las respuestas de nuestros controladores
            return new ResponseEntity(servicioUsuario.guardarUsuario(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity obtenerUsuario(@PathVariable("id") Long idUsuario){ //@pathVariable indica que el idUsuario esta ligado al id que se pasa por url
        return new ResponseEntity(servicioUsuario.obtenerUsuario(idUsuario),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity modificarUsuario(@PathVariable("id")Long idUsuario, @RequestBody Usuario usuario ){ //@pathVariable indica que el idUsuario esta ligado al id que se pasa por url y //RequestBody indicacamos que necesitamos un JSON de tipo usuario
        return new ResponseEntity(servicioUsuario.usuarioAModificar(idUsuario,usuario),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable("id")Long idUsuario){ //@pathVariable indica que el idUsuario esta ligado al id que se pasa por url y //RequestBody indicacamos que necesitamos un JSON de tipo usuario

        Boolean respuesta = servicioUsuario.eliminarUsuario(idUsuario);
        if(respuesta == true){
            return new ResponseEntity(HttpStatus.OK); //elimino al usuario
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND); // no encontro al usuario
        }
    }
}
