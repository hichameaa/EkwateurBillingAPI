package com.ekwateur.EkwateurBillingAPI.service;

import com.ekwateur.EkwateurBillingAPI.dto.IndividualBillingDetailsDTO;
import com.ekwateur.EkwateurBillingAPI.dto.IndividualClientDTO;
import com.ekwateur.EkwateurBillingAPI.dto.ProBillingDetailsDTO;
import com.ekwateur.EkwateurBillingAPI.dto.ProClientDTO;
import com.ekwateur.EkwateurBillingAPI.enums.Civility;
import com.ekwateur.EkwateurBillingAPI.enums.ProClientType;
import com.ekwateur.EkwateurBillingAPI.service.Impl.BillingServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BillingServiceImplTest {

    private final BillingServiceImpl billingService = new BillingServiceImpl();

    @Test
    void testCalculateProBill() {
        // Given
        ProClientDTO proClientDto = new ProClientDTO("EKW12345678", "12345678901234", "Entreprise XYZ", ProClientType.HIGH_TURNOVER);

        // When
        ProBillingDetailsDTO billDetails = billingService.calculateProBill(proClientDto, 1000, 500, LocalDate.now());

        // Then
        assertEquals(new BigDecimal("169.500"), billDetails.totalAmount());
    }

    @Test
    void testCalculateIndividualBill() {
        // Given
        IndividualClientDTO individualClientDto = new IndividualClientDTO("EKW87654321", Civility.M, "Dupont", "Jean");

        // When
        IndividualBillingDetailsDTO billDetails = billingService.calculateIndividualBill(individualClientDto, 800, 300, LocalDate.now());

        // Then
        assertEquals(new BigDecimal("131.300"), billDetails.totalAmount());
    }

    @Test
    void testInvalidClientReference() {
        // Given
        ProClientDTO proClientDto = new ProClientDTO("INVALID12345", "12345678901234", "Entreprise XYZ", ProClientType.HIGH_TURNOVER);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> billingService.calculateProBill(proClientDto, 1000, 500, LocalDate.now()));

    }
}
