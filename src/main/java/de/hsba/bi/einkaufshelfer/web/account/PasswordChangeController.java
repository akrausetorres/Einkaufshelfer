package de.hsba.bi.einkaufshelfer.web.account;


import de.hsba.bi.einkaufshelfer.user.User;
import de.hsba.bi.einkaufshelfer.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/account/passwort-change")
@RequiredArgsConstructor
public class PasswordChangeController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public String passwordChange(
            Model model,
            @ModelAttribute("passwordChangeForm") @Valid PasswordChangeForm form, BindingResult bindingResult) {

        model.addAttribute("pageTitle", "Mein Konto");
        model.addAttribute("pageDescription", "Verwalte dein Konto");

        if (bindingResult.hasErrors()) {
            return "account/settings";
        }

        String oldPassword = form.getOldPassword();
        String password = form.getNewPassword();
        String passwordConfirm = form.getNewPasswordConfirm();

        User user = userService.findCurrentUser();

        if (!password.equals(passwordConfirm)) {
            model.addAttribute("passwordChangeError", "Die beiden Passwörter stimmen nicht überein. Bitte erneut versuchen.");
            return "account/settings";
        }

        if ( !passwordEncoder.matches(oldPassword, user.getPassword()) ) {
            model.addAttribute("passwordChangeError", "Dein aktuelles Passwort ist nicht korrekt.");
            return "account/settings";
        }

        user.setPassword(passwordEncoder.encode(password));
        userService.saveUser(user);

        model.addAttribute("passwordChangeSuccess", "Das Passwort wurde erfolgreich geändert.");
        return "/account/settings";
    }
}
