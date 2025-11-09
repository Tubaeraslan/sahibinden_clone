package controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import starter.SahibindenCloneApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SahibindenCloneApplication.class)
@AutoConfigureMockMvc(addFilters = false) //güvelik filtresini kapat
public class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetBrandById_Returns200() throws Exception {
        mockMvc.perform(get("/rest/api/brands/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBrandById_NotFound() throws Exception {
        mockMvc.perform(get("/rest/api/brands/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateBrand_ValidationError() throws Exception {
        String json = "{\"name\": \"\"}";
        mockMvc.perform(post("/rest/api/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
