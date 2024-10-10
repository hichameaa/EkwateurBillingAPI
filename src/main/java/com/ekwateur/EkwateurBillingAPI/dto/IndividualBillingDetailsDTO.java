package com.ekwateur.EkwateurBillingAPI.dto;

import com.ekwateur.EkwateurBillingAPI.enums.Civility;

import java.math.BigDecimal;
import java.time.LocalDate;

public record IndividualBillingDetailsDTO(
        LocalDate billingDate,
        String clientReference,
        Civility civility,
        String lastName,
        String firstName,
        BigDecimal electricityRate,
        BigDecimal gasRate,
        BigDecimal electricityAmount,
        BigDecimal gasAmount,
        BigDecimal totalAmount
) {}
