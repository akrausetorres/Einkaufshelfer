package de.hsba.bi.einkaufshelfer.web.account;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterForm {

    @NotBlank(message = "Bitte einen Nutzernamen eingeben")
    private String username;

    @NotEmpty(message = "Bitte eine Rolle eingeben")
    private String role;

    @NotEmpty(message = "Bitte Passwort eingeben")
    private String password;

    @NotEmpty(message = "Bitte Passwort wiederholen eingeben")
    private String passwordConfirm;
}
