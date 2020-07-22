package de.hsba.bi.einkaufshelfer.web.account;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PasswordChangeForm {

    @NotEmpty(message = "Bitte das aktuelle Passwort eingeben")
    private String oldPassword;

    @NotEmpty(message = "Bitte Passwort eingeben")
    private String newPassword;

    @NotEmpty(message = "Bitte das neue Passwort bestätigen")
    private String newPasswordConfirm;
}