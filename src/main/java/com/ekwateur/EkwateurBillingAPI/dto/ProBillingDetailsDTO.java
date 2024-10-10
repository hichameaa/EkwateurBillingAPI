package com.ekwateur.EkwateurBillingAPI.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProBillingDetailsDTO (
        LocalDate billingDate,
        String clientReference,
        String corporateName,
        BigDecimal electricityRate,
        BigDecimal gasRate,
        BigDecimal electricityAmount,
        BigDecimal gasAmount,
        BigDecimal totalAmount
){}
