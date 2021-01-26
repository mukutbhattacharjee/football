package com.publicissapient.football.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author mukutbhattacharjee
 */

@SpringBootTest
@AutoConfigureMockMvc
public class FootballControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getsAllRides() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/countries")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
