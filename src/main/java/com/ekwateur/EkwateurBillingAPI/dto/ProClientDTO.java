package com.ekwateur.EkwateurBillingAPI.dto;

import com.ekwateur.EkwateurBillingAPI.enums.ProClientType;

/**
 * Data Transfer Object (DTO) for professional clients.
 */
public record ProClientDTO(
        String clientReference,
        String siret,
        String corporateName,
        ProClientType proClientType
) {}