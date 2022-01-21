package org.springframework.samples.distructor;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(value = "spring")
    void testGameList() throws Exception {
        mockMvc.perform(get("/games"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("games"))
            .andExpect(view().name("games/gameList"));
    }

    @Test
    @WithMockUser(value = "spring")
    void testGameCreationForm() throws Exception {
        mockMvc.perform(get("/games/new"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("game"))
            .andExpect(view().name("games/gameCreationForm"));
    }

    @Test
    @WithMockUser(value = "spring")
    void testGameCreationFormPost() throws Exception {
        mockMvc.perform(
                post("/games/new")
                    .param("name", "Prueba")
                    .param("hidden", "false")
                    .param("gameMode", "PUZZLE")
                    .with(csrf())
            ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring")
    void testGameCreationFormPostHasErrors() throws Exception {
        mockMvc.perform(
                post("/games/new")
                    .param("name", "")
                    .param("hidden", "false")
                    .param("gameMode", "PUZZLE")
                    .with(csrf())
            ).andExpect(status().isOk())
            .andExpect(model().attributeHasFieldErrors("game", "name"))
            .andExpect(view().name("games/gameCreationForm"));
    }

    @Test
    @WithMockUser(value = "spring")
    void testGetGame() throws Exception {
        mockMvc.perform(get("/games/currentGame"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring")
    void testJoinGame() throws Exception {
        mockMvc.perform(get("/games/join/3"))
            .andExpect(status().isOk());
    }
    
}
