package com.ekwateur.EkwateurBillingAPI.model;

import com.ekwateur.EkwateurBillingAPI.enums.EnergyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Class representing the energy consumption of a client.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnergyConsumption {

    private String clientReference;
    private Map<EnergyType, BigDecimal> consumption;
}
