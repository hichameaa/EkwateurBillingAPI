package com.ekwateur.EkwateurBillingAPI.model;

import com.ekwateur.EkwateurBillingAPI.enums.ProClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing a professional client.
 * This class extends the Client abstract class and adds specific attributes
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProClient extends Client {

    private String siret;
    private String corporateName;
    private ProClientType proClientType;

    public ProClient(String clientReference, String type, String siret, String corporateName, ProClientType proClientType) {
        super(clientReference, type);
        this.siret = siret;
        this.corporateName = corporateName;
        this.proClientType = proClientType;
    }
}