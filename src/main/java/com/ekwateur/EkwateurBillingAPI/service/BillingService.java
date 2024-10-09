package com.ekwateur.EkwateurBillingAPI.service;

import com.ekwateur.EkwateurBillingAPI.dto.EnergyConsumptionDTO;
import com.ekwateur.EkwateurBillingAPI.dto.IndividualClientDTO;
import com.ekwateur.EkwateurBillingAPI.dto.ProClientDTO;

import java.math.BigDecimal;

/**
 * Interface representing the billing service.
 * This interface defines the methods for billing operations
 */
public interface BillingService {

    BigDecimal calculateProBill(ProClientDTO proClientDto, int kWhElectricity, int kWhGas);

    BigDecimal calculateIndividualBill(IndividualClientDTO individualClientDto, int kWhElectricity, int kWhGas);
}
