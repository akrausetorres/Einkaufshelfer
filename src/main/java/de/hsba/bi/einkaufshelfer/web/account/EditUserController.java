package de.hsba.bi.einkaufshelfer.web.account;


import de.hsba.bi.einkaufshelfer.user.User;
import de.hsba.bi.einkaufshelfer.user.UserService;
import lombok.RequiredArgsConstructor;
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

        //make sure role is not manipulated, in case we give BOOTH
        String role = form.getRole() == User.HELPER_ROLE ? User.HELPER_ROLE : form.getRole() == User.NEEDY_ROLE ? User.NEEDY_ROLE : form.getRole() == User.BOOTH_ROLE ? User.BOOTH_ROLE : User.BOOTH_ROLE;
        String username = form.getUsername();

        User user = userService.findCurrentUser();

        user.setRole(role);
        user.setName(username);
        userService.saveUser(user);

        model.addAttribute("editUserSuccess", "Die Änderungen wurden gespeichert.");
        return "/account/settings";
    }
}
