package de.hsba.bi.einkaufshelfer.web;

import de.hsba.bi.einkaufshelfer.user.User;
import de.hsba.bi.einkaufshelfer.user.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String greetingString = auth instanceof AnonymousAuthenticationToken ? "!" : " " + SecurityContextHolder.getContext().getAuthentication().getName() + "!";

        model.addAttribute("pageTitle", "Hey" + greetingString);
        model.addAttribute("pageDescription", "Hier ensteht dein neuer Einkaufshelfer!");
        return "homepage";
    }
}
