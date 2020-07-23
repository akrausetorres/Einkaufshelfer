package de.hsba.bi.einkaufshelfer.web.account;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EditUserForm {

    @NotEmpty(message = "Bitte einen Nutzernamen eingeben")
    private String username;

    @NotEmpty(message = "Bitte eine Rolle eingeben")
    private String role;
}