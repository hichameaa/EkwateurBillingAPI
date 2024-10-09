package com.ekwateur.EkwateurBillingAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Abstract class representing a client.
 * This class serves as a base for different types of clients,
 * encapsulating common attributes and behaviors.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Client {

    private String clientReference;
    private String type;
}


