package ar.edu.unt.frc.tup.lciii.proyectoBasico.utils.validations.password;

/*Esta interface nos va a permitir crear nuestra propia anotacion @ValidPassoword
por eso el @interface puedo guiarme de las anotacions @notnull de player*/

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented //Genera la anotacion
//@Constraint Indica que es una jakartaBeansValidationConstraint

/*le paso una clase que es la que hace la validacion, esta implimenta la interface
ConstraintValidator y en la misma defino los metodos de validacion que quiero*/
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({TYPE,FIELD,ANNOTATION_TYPE})//es de tipo anotacion
@Retention(RUNTIME)// se va a ejecutar en tiempo de ejecucion

public @interface ValidPassword {

    String message() default "Invalid Password.";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
