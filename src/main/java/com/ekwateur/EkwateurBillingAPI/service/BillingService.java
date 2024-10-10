package com.ekwateur.EkwateurBillingAPI.service;

import com.ekwateur.EkwateurBillingAPI.dto.*;

import java.time.LocalDate;

/**
 * Interface representing the billing service.
 * This interface defines the methods for billing operations
 */
public interface BillingService {

    ProBillingDetailsDTO calculateProBill(ProClientDTO proClientDto, int kWhElectricity, int kWhGas, LocalDate billingDate);

    IndividualBillingDetailsDTO calculateIndividualBill(IndividualClientDTO individualClientDto, int kWhElectricity, int kWhGas, LocalDate billingDate);
}
