package ar.edu.unt.frc.tup.lciii.proyectoBasico.configs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*Test de llamada a una api(api de deocumentacion)*/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringDocConfigTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getDocumentation() throws IOException{
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("/v3/api-docs",String.class);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertNotNull(responseEntity.getBody());

        Path specs = Paths.get("docs/specs");
        Files.createDirectories(specs);
        Files.writeString(specs.resolve("swagger.json"), responseEntity.getBody());
    }
}
