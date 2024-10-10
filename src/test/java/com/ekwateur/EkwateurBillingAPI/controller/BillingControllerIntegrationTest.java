package com.ekwateur.EkwateurBillingAPI.controller;


import com.ekwateur.EkwateurBillingAPI.enums.Civility;
import com.ekwateur.EkwateurBillingAPI.enums.ProClientType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ekwateur.EkwateurBillingAPI.dto.IndividualClientDTO;
import com.ekwateur.EkwateurBillingAPI.dto.ProClientDTO;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class BillingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCalculateProBill() throws Exception {
        // Given
        ProClientDTO proClientDto = new ProClientDTO("EKW12345678", "12345678901234", "Entreprise XYZ", ProClientType.HIGH_TURNOVER);

        // When
        mockMvc.perform(post("/ekwateurbilling-api/calculate/pro")
                       .param("kWhElectricity", "1000")
                       .param("kWhGas", "500")
                       .param("billingDate", LocalDate.now().toString())
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(proClientDto)))

               // Then
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.totalAmount").value(169.500));
    }

    @Test
    void testCalculateIndividualBill() throws Exception {
        // Given
        IndividualClientDTO individualClientDto = new IndividualClientDTO("EKW87654321", Civility.M, "Dupont", "Jean");

        // When
        mockMvc.perform(post("/ekwateurbilling-api/calculate/individual")
                       .param("kWhElectricity", "800")
                       .param("kWhGas", "300")
                       .param("billingDate", LocalDate.now().toString())
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(individualClientDto)))

               // Then
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.totalAmount").value(131.300));
    }

    @Test
    void testInvalidClientReference() throws Exception {
        // Given
        ProClientDTO proClientDto = new ProClientDTO("INVALID12345", "12345678901234", "Entreprise XYZ", ProClientType.HIGH_TURNOVER);

        // When
        mockMvc.perform(post("/ekwateurbilling-api/calculate/pro")
                       .param("kWhElectricity", "1000")
                       .param("kWhGas", "500")
                       .param("billingDate", LocalDate.now().toString())
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(proClientDto)))

               // Then
               .andExpect(status().isBadRequest());
    }
}
