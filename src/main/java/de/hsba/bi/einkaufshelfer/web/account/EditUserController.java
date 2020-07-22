package de.hsba.bi.einkaufshelfer.web.account;


import de.hsba.bi.einkaufshelfer.user.User;
import de.hsba.bi.einkaufshelfer.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/account/edit")
@RequiredArgsConstructor
public class EditUserController {

    private final UserService userService;

    @PostMapping
    public String editUser(
            Model model,
            @ModelAttribute("editUserForm") @Valid EditUserForm form, BindingResult bindingResult) {

        model.addAttribute("pageTitle", "Mein Konto");
        model.addAttribute("pageDescription", "Verwalte dein Konto");

        if (bindingResult.hasErrors()) {
            return "account/settings";
        }

        User user = userService.findCurrentUser();

        //make sure role is not manipulated, in case we give BOOTH
        String role = form.getRole() == User.HELPER_ROLE ? User.HELPER_ROLE : form.getRole() == User.NEEDY_ROLE ? User.NEEDY_ROLE : form.getRole() == User.BOOTH_ROLE ? User.BOOTH_ROLE : User.BOOTH_ROLE;
        String username = form.getUsername().toLowerCase();
        Boolean changedUsername = !username.equals(user.getName());

        if(changedUsername) {
            // Logout because token is changed
            // UX can be make better by updating the token in background
            SecurityContextHolder.clearContext();
            model.addAttribute("pageTitle", "Login");
            model.addAttribute("pageDescription", "Anmelden & Konto verwalten");
        }

        user.setRole(role);
        user.setName(username);

        userService.saveUser(user);

        model.addAttribute("editUserSuccess", changedUsername ? "Nutzername wurde geändert. Bitte erneut einloggen." : "Die Änderungen wurden gespeichert.");
        return changedUsername ? "/account/login" : "/account/settings";
    }
}
