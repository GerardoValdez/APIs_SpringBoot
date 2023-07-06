package ar.edu.unt.frc.tup.lciii.proyectoBasico.utils.validations.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.Arrays;

/*Aca implementamos nuestra validacion customizada y la usamos con la anotacion @ValidPassword*/

/*ConstraintValidator pide dos argumentos, la anotacion y el tipo de dato que se va a validar*/
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword,String> {
    @Override
    public void initialize(ValidPassword arg0) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        //Todas las clases de este metodo son de passay
        //creamos el validador que ya contiene una serie de validaciones
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8,30), //min y max de letras
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 3, false),// no secuencias de numeros(valida hasta 3 caracteres)
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 3, false),// no secuencias de letras(valida hasta 3 caracteres)
                new IllegalSequenceRule(EnglishSequenceData.USQwerty, 3, false),// no secuencias de teclado(valida hasta 3 caracteres)
                new WhitespaceRule()
                )
        );

        RuleResult result = validator.validate(new PasswordData(password));
        if(result.isValid())
            return true;
        else{
            //fuerzo la detencion del contex ya que hubo un error
            // construyo las constraint que no pasaron la validacion
            //creo un string joineando todos los msj de todas las constraint que no paso la password
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
            String.join(",", validator.getMessages(result))).addConstraintViolation();

            return false;
        }
    }
}
