package com.ekwateur.EkwateurBillingAPI.controller;

import com.ekwateur.EkwateurBillingAPI.dto.IndividualBillingDetailsDTO;
import com.ekwateur.EkwateurBillingAPI.dto.IndividualClientDTO;
import com.ekwateur.EkwateurBillingAPI.dto.ProBillingDetailsDTO;
import com.ekwateur.EkwateurBillingAPI.dto.ProClientDTO;
import com.ekwateur.EkwateurBillingAPI.service.BillingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Controller for managing billing operations.
 * <p>
 * This class is responsible for handling requests related to the
 * billing API of the application.
 */
@RestController
@RequestMapping("/ekwateurbilling-api")
@Slf4j
public class BillingController {

    private final BillingService billingService;

    /**
     * Constructor for BillingController.
     *
     * @param billingService the billing service to be used
     */
    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping("/calculate/pro")
    public ResponseEntity<ProBillingDetailsDTO> calculateProBill(
            @RequestBody ProClientDTO proClientDto,
            @RequestParam int kWhElectricity,
            @RequestParam int kWhGas,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate billingDate) {
        ProBillingDetailsDTO billingDetails = billingService.calculateProBill(proClientDto, kWhElectricity, kWhGas, billingDate);
        return ResponseEntity.ok(billingDetails);
    }


    @PostMapping("/calculate/individual")
    public ResponseEntity<IndividualBillingDetailsDTO> calculateIndividualBill(
            @RequestBody IndividualClientDTO individualClientDto,
            @RequestParam int kWhElectricity,
            @RequestParam int kWhGas,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate billingDate) {
        IndividualBillingDetailsDTO billingDetails = billingService.calculateIndividualBill(individualClientDto, kWhElectricity, kWhGas, billingDate);
        return ResponseEntity.ok(billingDetails);
    }
}
