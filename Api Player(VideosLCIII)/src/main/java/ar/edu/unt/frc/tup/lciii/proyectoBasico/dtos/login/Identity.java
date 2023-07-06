package ar.edu.unt.frc.tup.lciii.proyectoBasico.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*Anotaciones de la libreria Jackson (nos ayuda a convertir de un JSON a objeto)
 * @JsonTypeInfo: esta indicando que la esta clase va a usar por nombre 2 subtipos diferenciados
 *  a traves de la propiedad identity_type y que esta incluida dentro de la clase
 *
 *La calse identity extiende en esos dos subtipos */
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property = "identity_type", include = JsonTypeInfo.As.EXISTING_PROPERTY, visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserNameIdentity.class, name = "USERNAME"), // mapea un UseraNameIdentity cuando venga un USERNAME
        @JsonSubTypes.Type(value = EmailIdentity.class, name = "EMAIL")

})

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class  Identity {


    //Notaciones de la documentacion
    @Schema(title = "Type of identity used to logged in",
            description ="Type of identity provided to logged in" ,
            example = "USERNAME or EMAIL",
            nullable = false)

    @NotNull(message = "identity_type can't by null")
    @JsonProperty("identity_type") //declaro la propiedad
    private IdentityType identityType;
}
