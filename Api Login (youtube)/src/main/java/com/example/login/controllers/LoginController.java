package com.example.login.controllers;

import com.example.login.models.User;
import com.example.login.services.UserServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@RestController: Indica que la clase es un controlador de tipo REST.
Esta anotación combina la anotación @Controller de Spring MVC y @ResponseBody,
lo que significa que cada método del controlador se trata como un punto final REST
y la respuesta se serializa automáticamente como JSON (u otro formato, según la configuración)
antes de ser devuelta al cliente.*/

/*@CrossOrigin(origins = "*"): Permite solicitudes de diferentes orígenes (dominios) al controlador.
El asterisco (*) indica que se permite cualquier origen. Esto es útil cuando deseas permitir el acceso
a tu API desde diferentes dominios o aplicaciones front-end.*/

/*@RequestMapping(path = "api/users"): Especifica la ruta base para todas las solicitudes
 en el controlador. En este caso, las rutas del controlador comenzarán con "/api/v1/users".
 Por ejemplo, si hay un método en el controlador con la anotación */

/*En resumen, estas anotaciones configuran el controlador para que responda a las solicitudes REST
 en la ruta base "/api/v1/users" y permitan el acceso desde diferentes orígenes.
 Esto es útil para construir una API RESTful y permitir la comunicación con clientes externos*/
@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/users")
public class LoginController {
    @Autowired
    @Qualifier("userServiceImplement")
    UserServiceInterface userService;



    /*En resumen, @Valid se encarga de aplicar las validaciones definidas en la clase User antes
    de ejecutar el método, y @RequestBody indica que el objeto User se obtendrá del cuerpo de la
    solicitud HTTP. Juntas, estas anotaciones permiten recibir y validar el objeto User proporcionado
    en el cuerpo de la solicitud en el método authenticateUser.*/
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody User user){
        try {
            User userLogin = userService.authenticateUser(user);
            String mensaje = "Bienvenido " + userLogin.getUsername();
            return ResponseEntity.status(HttpStatus.OK).body(mensaje);
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user){
        try{
            boolean noRegisteredUser = userService.noRegisteredUser(user);
            if (noRegisteredUser){
                try{
                    User createUser = userService.createUser(user);
                    String menssage = "Usuario creado: " + createUser.getUsername();
                    return ResponseEntity.status(HttpStatus.OK).body(menssage);
                }
                catch (Exception e){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al ingresar datos.");
                }
            }else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya está registrado.");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al verificar.");
        }
    }




    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUser(){
        try {
            var users = userService.getAllUsers();
                return ResponseEntity.status(HttpStatus.OK).body(users);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



    /*@PathVariable es una anotación que permite vincular un valor de una variable de ruta
    de una URL a un parámetro de método en un controlador de Spring. Esto facilita la captura
    y utilización de valores dinámicos en las rutas de tus endpoints.*/
    @GetMapping("/findUserById/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        try{
            User user = userService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("Delete Success.");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Data Found.");
        }
    }



    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User>updateUser(@Valid @RequestBody User user, @PathVariable Long id){
        try{
            User updateUser = userService.updateUser(user,id);
            return ResponseEntity.status(HttpStatus.OK).body(updateUser);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }
    }

}
