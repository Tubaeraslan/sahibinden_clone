package controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import starter.SahibindenCloneApplication;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = SahibindenCloneApplication.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUserById_Returns200() throws Exception {
        mockMvc.perform(get("/rest/api/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUserById_NotFound() throws Exception {
        mockMvc.perform(get("/rest/api/users/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateUser_ValidationError() throws Exception {
        String json = "{\"userName\": \"\", \"email\": \"\", \"password\": \"\"}";
        mockMvc.perform(post("/rest/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}