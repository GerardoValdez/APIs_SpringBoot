package ar.edu.unt.frc.tup.lciii.proyectoBasico.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*Vamos a usar una libreria de JACKSON para que haga el mapeo
de las clases de models a entity y viceversa*/
@Configuration //indica que dentro de la clase hay metodos que levantan beans
public class MappersConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    /*
    Esta anotación se utiliza para indicar inequivocamente cual es el nombre que
    tendrá un Bean. De esta manera podemos tener mas de Bean del mismo tipo en al ApplicationContext.
     */
    @Qualifier("mergerMapper") // este mapeador lo vamos a utilizar para hacer los patch
    public ModelMapper mergerMapper() {
        ModelMapper mapper =  new ModelMapper();
        mapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }


    /*Le registramos un modulo JavaTimeModule para que interprete el LocalDateTime*/
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }
}
