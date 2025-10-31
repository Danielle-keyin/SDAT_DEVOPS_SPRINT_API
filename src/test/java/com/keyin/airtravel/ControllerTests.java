package com.keyin.airtravel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    void listCities_OK() throws Exception {
        mvc.perform(get("/cities"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void q1_airportsInCity_OK() throws Exception {
        mvc.perform(get("/cities/1/airports"))
                .andExpect(status().isOk());
    }

    @Test
    void q2_aircraftByPassenger_OK() throws Exception {
        mvc.perform(get("/passengers/1/aircraft"))
                .andExpect(status().isOk());
    }

    @Test
    void q3_airportsByAircraft_OK() throws Exception {
        mvc.perform(get("/aircraft/1/airports"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.origins").exists())
                .andExpect(jsonPath("$.destinations").exists());
    }

    @Test
    void q4_airportsUsedByPassenger_OK() throws Exception {
        mvc.perform(get("/passengers/1/airports"))
                .andExpect(status().isOk());
    }
}
