package com.ekwateur.EkwateurBillingAPI.controller;

import com.ekwateur.EkwateurBillingAPI.service.BillingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
