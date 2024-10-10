package com.ekwateur.EkwateurBillingAPI.service.Impl;

import com.ekwateur.EkwateurBillingAPI.dto.IndividualBillingDetailsDTO;
import com.ekwateur.EkwateurBillingAPI.dto.IndividualClientDTO;
import com.ekwateur.EkwateurBillingAPI.dto.ProBillingDetailsDTO;
import com.ekwateur.EkwateurBillingAPI.dto.ProClientDTO;
import com.ekwateur.EkwateurBillingAPI.enums.ProClientType;
import com.ekwateur.EkwateurBillingAPI.model.Client;
import com.ekwateur.EkwateurBillingAPI.model.IndividualClient;
import com.ekwateur.EkwateurBillingAPI.model.ProClient;
import com.ekwateur.EkwateurBillingAPI.service.BillingService;
import com.ekwateur.EkwateurBillingAPI.util.BillingRates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.ekwateur.EkwateurBillingAPI.util.BillingRates.*;

/**
 * Implementation of the BillingService interface.
 * This class contains the business logic for billing operations,
 */
@Service
@Slf4j
public class BillingServiceImpl implements BillingService {

    private static final String CLIENT_REFERENCE_REGEX = "^EKW\\d{8}$";

    @Override
    public ProBillingDetailsDTO calculateProBill(ProClientDTO proClientDto, int kWhElectricity, int kWhGas, LocalDate billingDate) {
        validateClientReference(proClientDto.clientReference());

        log.info("Calcul de la facture pour le client professionnel : {}", proClientDto.clientReference());
        ProBillingDetailsDTO ProBillDetails = calculateProBillDetails(proClientDto, kWhElectricity, kWhGas, billingDate);

        log.info("Facture pour le client professionnel {} est {}", proClientDto.clientReference(), ProBillDetails);
        return ProBillDetails;
    }

    @Override
    public IndividualBillingDetailsDTO calculateIndividualBill(IndividualClientDTO individualClientDto, int kWhElectricity, int kWhGas, LocalDate billingDate) {
        validateClientReference(individualClientDto.clientReference());

        log.info("Calcul de la facture pour le client particulier : {}", individualClientDto.clientReference());
        IndividualBillingDetailsDTO IndividualBillDetails = calculateIndividualBillDetails(individualClientDto, kWhElectricity, kWhGas, billingDate);

        log.info("Facture pour le client particulier {} est {}", individualClientDto.clientReference(), IndividualBillDetails);
        return IndividualBillDetails;
    }

    private ProBillingDetailsDTO calculateProBillDetails(ProClientDTO proClientDto, int kWhElectricity, int kWhGas, LocalDate billingDate) {
        BigDecimal electricityRate = proClientDto.proClientType() == ProClientType.HIGH_TURNOVER ?
                BillingRates.ELECTRICITY_RATE_PRO_HIGH_TURNOVER :
                BillingRates.ELECTRICITY_RATE_PRO_LOW_TURNOVER;

        BigDecimal gasRate = proClientDto.proClientType() == ProClientType.HIGH_TURNOVER ?
                BillingRates.GAS_RATE_PRO_HIGH_TURNOVER :
                BillingRates.GAS_RATE_PRO_LOW_TURNOVER;

        BigDecimal electricityAmount = BigDecimal.valueOf(kWhElectricity).multiply(electricityRate);
        BigDecimal gasAmount = BigDecimal.valueOf(kWhGas).multiply(gasRate);
        BigDecimal totalAmount = electricityAmount.add(gasAmount);

        return new ProBillingDetailsDTO(
                billingDate,
                proClientDto.clientReference(),
                proClientDto.corporateName(),
                electricityRate,
                gasRate,
                electricityAmount,
                gasAmount,
                totalAmount
        );
    }

    private IndividualBillingDetailsDTO calculateIndividualBillDetails(IndividualClientDTO individualClientDto, int kWhElectricity, int kWhGas, LocalDate billingDate) {
        BigDecimal electricityRate = BillingRates.ELECTRICITY_RATE_INDIVIDUAL;
        BigDecimal gasRate = BillingRates.GAS_RATE_INDIVIDUAL;

        BigDecimal electricityAmount = BigDecimal.valueOf(kWhElectricity).multiply(electricityRate);
        BigDecimal gasAmount = BigDecimal.valueOf(kWhGas).multiply(gasRate);
        BigDecimal totalAmount = electricityAmount.add(gasAmount);

        return new IndividualBillingDetailsDTO(
                billingDate,
                individualClientDto.clientReference(),
                individualClientDto.civility(),
                individualClientDto.lastName(),
                individualClientDto.firstName(),
                electricityRate,
                gasRate,
                electricityAmount,
                gasAmount,
                totalAmount
        );
    }

    private void validateClientReference(String clientReference) {
        if (clientReference == null || !clientReference.matches(CLIENT_REFERENCE_REGEX)) {
            throw new IllegalArgumentException("La référence client est invalide.");
        }
    }

}
