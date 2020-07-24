package de.hsba.bi.einkaufshelfer.web.account;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class EditAddressForm {
    @NotEmpty(message = "Bitte eine Straße eingeben")
    private String street;

    @NotEmpty(message = "Bitte eine Hausnummer eingeben")
    private String streetNr;

    @NotEmpty(message = "Bitte eine Postleitzahl eingeben")
    private String postalcode;

    @NotEmpty(message = "Bitte eine Stadt eingeben")
    private String city;

    @NotEmpty(message = "Bitte ein Land eingeben")
    private String country;
}
