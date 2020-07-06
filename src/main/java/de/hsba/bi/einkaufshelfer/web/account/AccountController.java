package de.hsba.bi.einkaufshelfer.web.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("pageTitle", "Login");
        model.addAttribute("pageDescription", "Anmelden & Konto verwalten");
        return "account/login";
    }

    @GetMapping("forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute("pageTitle", "Passwort vergessen?!");
        model.addAttribute("pageDescription", "Wir schicken dir ein Link zum Zurücksetzen.");
        return "account/forgot-password";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("pageTitle", "Registrieren");
        model.addAttribute("pageDescription", "Erstelle ein neues Konto");
        return "account/register";
    }
}
