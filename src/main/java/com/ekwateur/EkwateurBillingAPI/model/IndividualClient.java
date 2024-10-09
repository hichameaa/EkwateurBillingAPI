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
}
