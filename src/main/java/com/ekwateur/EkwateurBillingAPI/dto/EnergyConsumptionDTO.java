package com.ekwateur.EkwateurBillingAPI.dto;

import com.ekwateur.EkwateurBillingAPI.enums.EnergyType;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Data Transfer Object (DTO) for energy consumption.
 */
public record EnergyConsumptionDTO(
        String clientReference,
        Map<EnergyType, BigDecimal> consumption
) {}


