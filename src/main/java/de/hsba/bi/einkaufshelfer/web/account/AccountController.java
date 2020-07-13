package de.hsba.bi.einkaufshelfer.web.account;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("pageTitle", "Login");
        model.addAttribute("pageDescription", "Anmelden & Konto verwalten");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth instanceof AnonymousAuthenticationToken ? "account/login" : "redirect:/account/settings";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("pageTitle", "Registrieren");
        model.addAttribute("pageDescription", "Erstelle ein neues Konto");
        return "account/register";
    }

    @GetMapping("settings")
    public String settings(Model model) {
        model.addAttribute("pageTitle", "Mein Konto");
        model.addAttribute("pageDescription", "Verwalte dein Konto");
        return "account/settings";
    }
}
