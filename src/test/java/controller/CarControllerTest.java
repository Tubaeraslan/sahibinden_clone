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
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetCarById_Returns200() throws Exception {
        mockMvc.perform(get("/rest/api/cars/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCarById_NotFound() throws Exception {
        mockMvc.perform(get("/rest/api/cars/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateCar_ValidationError() throws Exception {
        String json = "{\"brand\": \"\", \"model\": \"\", \"year\": null}";
        mockMvc.perform(post("/rest/api/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
