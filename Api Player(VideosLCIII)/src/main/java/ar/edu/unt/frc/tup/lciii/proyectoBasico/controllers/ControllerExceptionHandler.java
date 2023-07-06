package ar.edu.unt.frc.tup.lciii.proyectoBasico.controllers;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.common.ErrorApi;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

/*Creamos esta clase como interceptor de todas las expeciones que no están siendo
controladas los los controller asi enviamos mensajes amigables al usuario
(es nuestra ultima linea de defensa para agarrar una excepcion particular)*/

/*Todas las excepciones derivan de la clase Exception esta es la más general por lo que si
ninguna de mis expeciones particulares agarra cierta excepcion, va a ser agarrada por
 @ExceptionHandler(Exception.class) */


//@ControllerAdvice verifica la ejecucion de los controlladores
@ControllerAdvice
public class ControllerExceptionHandler {

    /*
    Se utiliza para manejar excepciones específicas en un controlador.
    Permite definir un método que se ejecutará cuando ocurra una excepción
    particular, brindando una respuesta adecuada al cliente.
     */
    @ExceptionHandler(Exception.class) //La más general
    public ResponseEntity<ErrorApi> handleError(Exception exception) {
        ErrorApi error = buildError(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //Captura cuando el argumento no es valido
    public ResponseEntity<ErrorApi> handleError(MethodArgumentNotValidException exception) {
        ErrorApi error = buildError(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ResponseStatusException.class) //la excepcion del createUser
    public ResponseEntity<ErrorApi> handleError(ResponseStatusException exception) {
        ErrorApi error = buildError(exception.getReason(), HttpStatus.valueOf(exception.getStatusCode().value()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(EntityNotFoundException.class) //la excepcion del createUser
    public ResponseEntity<ErrorApi> handleError(EntityNotFoundException exception) {
        ErrorApi error = buildError("Jugador no encontrado", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /*Ya que ErrorApi tiene la anotacion @Builder puedo contruir mi propia excepcion */
    private ErrorApi buildError(String message, HttpStatus status) {
        return ErrorApi.builder()
                .timestamp(String.valueOf(Timestamp.from(ZonedDateTime.now().toInstant())))
                .error(status.getReasonPhrase())
                .status(status.value())
                .message(message)
                .build();
    }
}
