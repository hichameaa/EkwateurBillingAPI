package com.ekwateur.EkwateurBillingAPI.model;

import com.ekwateur.EkwateurBillingAPI.enums.Civility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing an individual client.
 * This class extends the Client abstract class and adds specific attributes.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndividualClient extends Client {

    private Civility civility;
    private String lastName;
    private String firstName;

    public IndividualClient(String clientReference, String type, Civility civility, String lastName, String firstName) {
        super(clientReference, type);
        this.civility = civility;
        this.lastName = lastName;
        this.firstName = firstName;
    }

}
