package com.ekwateur.EkwateurBillingAPI.dto;

import com.ekwateur.EkwateurBillingAPI.enums.Civility;

/**
 * Data Transfer Object (DTO) for individual clients.
 */
public record IndividualClientDTO(
        String clientReference,
        Civility civility,
        String lastName,
        String firstName
) {}