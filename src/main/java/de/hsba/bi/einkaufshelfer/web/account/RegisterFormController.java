package de.hsba.bi.einkaufshelfer.web.account;

import de.hsba.bi.einkaufshelfer.user.User;
import de.hsba.bi.einkaufshelfer.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/account/register")
@RequiredArgsConstructor
public class RegisterFormController {

    private final UserService userService;

    @PostMapping
    public String create(
            Model model,
            @ModelAttribute("registerForm") @Valid RegisterForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "account/register";
        }

        String username = form.getUsername();
        String password = form.getPassword();
        String passwordConfirm = form.getPasswordConfirm();
        String role = form.getRole();

        User usercheck = userService.findUser(username);

        if (usercheck != null) {
            model.addAttribute("registerError", "Der Nutzername wird bereits verwendet");
            return "account/register";
        }

        if (!password.equals(passwordConfirm)) {
            model.addAttribute("registerError", "Die beiden Passwörter stimmen nicht überein. Bitte erneut versuchen.");
            return "account/register";
        }

        userService.createUser(username, password, role);
        return "redirect:/account/login";
    }
}
