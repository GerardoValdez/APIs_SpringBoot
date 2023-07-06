package ar.edu.unt.frc.tup.lciii.proyectoBasico.controllersTest;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.controllers.PlayerController;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Player;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.services.PlayerServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/*Este test verifica que el controlador HealthAppController devuelva pong*/
@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerServiceInterface playerServiceInterface;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getPlayerByIdTest() throws Exception{
        Player player = new Player();
        player.setId(1L);
        player.setUserName("valdez");
        player.setPassword("Password03#");
        player.setEmail("email@email.com");

        when(playerServiceInterface.getPlayerById(1L)).thenReturn(player);
        this.mockMvc.perform(get("/players/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("valdez"))
                .andExpect(jsonPath("$.email").value("email@email.com"))
                .andExpect(jsonPath("$.password").value("Password03#"));

        //--------------------------- 2formas de validar ---------------------------------

        MvcResult mvcResult = this.mockMvc.perform(get("/players/1")).andDo(print())
                .andExpect(status().isOk()).andReturn();

        Player result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Player.class);

        Assertions.assertEquals("valdez",result.getUserName());

    }
}
