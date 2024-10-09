package com.ekwateur.EkwateurBillingAPI.controller;

import com.ekwateur.EkwateurBillingAPI.dto.IndividualClientDTO;
import com.ekwateur.EkwateurBillingAPI.dto.ProClientDTO;
import com.ekwateur.EkwateurBillingAPI.service.BillingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
    public ResponseEntity<BigDecimal> calculateProBill(@RequestBody ProClientDTO proClientDto, @RequestParam int kWhElectricity, @RequestParam int kWhGas) {
        BigDecimal billAmount = billingService.calculateProBill(proClientDto, kWhElectricity, kWhGas);
        return ResponseEntity.ok(billAmount);
    }


    @PostMapping("/calculate/individual")
    public ResponseEntity<BigDecimal> calculateIndividualBill(@RequestBody IndividualClientDTO individualClientDto, @RequestParam int kWhElectricity, @RequestParam int kWhGas) {
        BigDecimal billAmount = billingService.calculateIndividualBill(individualClientDto, kWhElectricity, kWhGas);
        return ResponseEntity.ok(billAmount);
    }
}
