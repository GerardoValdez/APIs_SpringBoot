package com.example.login.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*supongamos que tienes una entidad UserEntity que representa un usuario en tu base de datos,
y también tienes un DTO UserDTO que se utiliza para transferir datos de usuario entre tu API
y la capa de presentación. Un mapeador de objetos puede ayudarte a convertir automáticamente
un objeto UserEntity a un objeto UserDTO y viceversa, sin tener que escribir código manualmente
para copiar cada propiedad.*/
@Configuration //Define una configuración específica para los mappers en la aplicación.
public class MappersConfig {

    /*crea y devuelve una instancia de ModelMapper*/
    /*La anotación @Bean se utiliza para marcar el método modelMapper().
    Esto le indica a Spring que debe crear y administrar una instancia de ModelMapper.
    Cada vez que se necesite un objeto ModelMapper en la aplicación, Spring devolverá
    la instancia administrada por el contenedor.*/
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    /*ModelMapper con la configuración específica de mergerMapper se utilizará para
     realizar mapeos donde solo se deseen copiar las propiedades que tengan un valor no nulo.*/
    @Bean
    @Qualifier("mergerMapper")
    public ModelMapper mergerMapper(){
        ModelMapper mergeMapper = new ModelMapper();
        mergeMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mergeMapper;
    }


    /*crea y devuelve una instancia de ObjectMapper. ObjectMapper es una biblioteca de Jackson
    que permite convertir objetos Java en JSON y viceversa. Es útil para la serialización y
    deserialización de objetos.*/
    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }

}
