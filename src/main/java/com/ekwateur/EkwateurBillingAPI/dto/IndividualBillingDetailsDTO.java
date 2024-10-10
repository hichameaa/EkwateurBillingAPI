package com.ekwateur.EkwateurBillingAPI.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record IndividualBillingDetailsDTO(
        LocalDate billingDate,
        String clientReference,
        String civility,
        String clientName,
        BigDecimal electricityRate,
        BigDecimal gasRate,
        BigDecimal electricityAmount,
        BigDecimal gasAmount,
        BigDecimal totalAmount
) {}
