package com.ekwateur.EkwateurBillingAPI.util;

import java.math.BigDecimal;

/**
 * Class holding billing rates for different clients.
 */
public class BillingRates {
    public static final BigDecimal ELECTRICITY_RATE_INDIVIDUAL = new BigDecimal("0.121");
    public static final BigDecimal GAS_RATE_INDIVIDUAL = new BigDecimal("0.115");
    public static final BigDecimal ELECTRICITY_RATE_PRO_HIGH_TURNOVER = new BigDecimal("0.114");
    public static final BigDecimal GAS_RATE_PRO_HIGH_TURNOVER = new BigDecimal("0.111");
    public static final BigDecimal ELECTRICITY_RATE_PRO_LOW_TURNOVER = new BigDecimal("0.118");
    public static final BigDecimal GAS_RATE_PRO_LOW_TURNOVER = new BigDecimal("0.113");
}
