package com.example.login.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*la clase SpringDocConfig configura la documentación de la
API utilizando propiedades de configuración y crea el objeto OpenAPI con la
información necesaria, como el nombre, la descripción, la versión y el contacto
del desarrollador. Esto permite generar la documentación de la API de manera automática
y coherente con la configuración de tu aplicación.*/




/*@Configuration se utiliza para marcar una clase de configuración en Spring.
Indica que la clase contiene métodos que definen la configuración de la aplicación,
como la creación de beans y la configuración de componentes.*/

/*@ConfigurationProperties Para enlazar automáticamente propiedades de configuración con los campos de la clase.
Esto permite una fácil configuración y acceso a las propiedades en un archivo de configuración,
como application.properties.*/


@Configuration
@ConfigurationProperties(prefix = "app")
public class SpringDocConfig {

    private String url;

    private String devName;

    private String devEmail;

    @Bean
    public OpenAPI openApi (
            /*
            Esta anotación se utiliza para inyectar valores de propiedades en los
            campos de una clase. Permite que las propiedades definidas en un archivo
            de configuración (como application.properties) sean inyectadas en las variables correspondientes.
             */
            @Value("${app.name}") String appName,
            @Value("${app.desc}") String appDescription,
            @Value("${app.version}") String appVersion){

        Info info = new Info()
                .title(appName)
                .version(appVersion)
                .description(appDescription)
                .contact(
                        new Contact()
                                .name(devName)
                                .email(devEmail));

        Server server = new Server()
                .url(url)
                .description(appDescription);

        return new OpenAPI()
                .components(new Components())
                .info(info)
                .addServersItem(server);
    }

}
