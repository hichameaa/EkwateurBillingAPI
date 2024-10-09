package com.ekwateur.EkwateurBillingAPI.service.Impl;

import com.ekwateur.EkwateurBillingAPI.dto.IndividualClientDTO;
import com.ekwateur.EkwateurBillingAPI.dto.ProClientDTO;
import com.ekwateur.EkwateurBillingAPI.enums.ProClientType;
import com.ekwateur.EkwateurBillingAPI.model.Client;
import com.ekwateur.EkwateurBillingAPI.model.IndividualClient;
import com.ekwateur.EkwateurBillingAPI.model.ProClient;
import com.ekwateur.EkwateurBillingAPI.service.BillingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
    public BigDecimal calculateProBill(ProClientDTO proClientDto, int kWhElectricity, int kWhGas) {
        validateClientReference(proClientDto.clientReference());

        log.info("Calcul de la facture pour le client professionnel : {}", proClientDto.clientReference());
        ProClient proClient = convertToProClient(proClientDto);
        BigDecimal bill = calculateBill(proClient, kWhElectricity, kWhGas);

        log.info("Montant total de la facture pour le client professionnel {} est {}", proClientDto.clientReference(), bill);
        return bill;
    }

    @Override
    public BigDecimal calculateIndividualBill(IndividualClientDTO individualClientDto, int kWhElectricity, int kWhGas) {
        validateClientReference(individualClientDto.clientReference());

        log.info("Calcul de la facture pour le client particulier : {}", individualClientDto.clientReference());
        IndividualClient individualClient = convertToIndividualClient(individualClientDto);
        BigDecimal bill = calculateBill(individualClient, kWhElectricity, kWhGas);

        log.info("Montant total de la facture pour le client particulier {} est {}", individualClientDto.clientReference(), bill);
        return bill;
    }

    private BigDecimal calculateBill(Client client, int kWhElectricity, int kWhGas) {
        BigDecimal electricityRate;
        BigDecimal gasRate;

        if (client instanceof ProClient proClient) {
            electricityRate = proClient.getProClientType() == ProClientType.HIGH_TURNOVER ? ELECTRICITY_RATE_BUSINESS_HIGH_TURNOVER : ELECTRICITY_RATE_BUSINESS_LOW_TURNOVER;
            gasRate = proClient.getProClientType() == ProClientType.HIGH_TURNOVER ? GAS_RATE_BUSINESS_HIGH_TURNOVER : GAS_RATE_BUSINESS_LOW_TURNOVER;
        } else {
            electricityRate = ELECTRICITY_RATE_INDIVIDUAL;
            gasRate = GAS_RATE_INDIVIDUAL;
        }

        return BigDecimal.valueOf(kWhElectricity).multiply(electricityRate)
                         .add(BigDecimal.valueOf(kWhGas).multiply(gasRate));
    }

    private void validateClientReference(String clientReference) {
        if (clientReference == null || !clientReference.matches(CLIENT_REFERENCE_REGEX)) {
            throw new IllegalArgumentException("La référence client est invalide.");
        }
    }

    private ProClient convertToProClient(ProClientDTO proClientDto) {
        return new ProClient(
                proClientDto.clientReference(),
                "PRO",
                proClientDto.siret(),
                proClientDto.corporateName(),
                proClientDto.proClientType()
        );
    }

    private IndividualClient convertToIndividualClient(IndividualClientDTO individualClientDto) {
        return new IndividualClient(
                individualClientDto.clientReference(),
                "INDIVIDUAL",
                individualClientDto.civility(),
                individualClientDto.lastName(),
                individualClientDto.firstName()
        );
    }
}
