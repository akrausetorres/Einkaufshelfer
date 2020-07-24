package de.hsba.bi.einkaufshelfer.web.account;

import de.hsba.bi.einkaufshelfer.address.Address;
import de.hsba.bi.einkaufshelfer.address.AddressService;
import de.hsba.bi.einkaufshelfer.user.User;
import de.hsba.bi.einkaufshelfer.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("pageTitle", "Login");
        model.addAttribute("pageDescription", "Anmelden & Konto verwalten");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth instanceof AnonymousAuthenticationToken ? "account/login" : "redirect:/account/settings";
    }

    @GetMapping("logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/account/login";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("pageTitle", "Registrieren");
        model.addAttribute("pageDescription", "Erstelle ein neues Konto");
        model.addAttribute("registerForm", new RegisterForm());
        return "account/register";
    }
}
