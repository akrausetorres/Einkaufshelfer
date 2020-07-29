package de.hsba.bi.einkaufshelfer.web.account;

import de.hsba.bi.einkaufshelfer.user.User;
import de.hsba.bi.einkaufshelfer.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final UserService userService;

    @GetMapping
    public String search(Model model, @RequestParam(value = "search", required = false, defaultValue = "") String search) {
        model.addAttribute("pageTitle", "Suche: ");
        model.addAttribute("pageDescription", "Diese Seite enthält Ergebnisse deiner Suche");

        //TODO: Search for HelpRequest with Cities

        // Search for Usernames
        List<User> foundUsers = userService.searchForUsername(search);
        model.addAttribute("foundUsers", foundUsers);

        return "search";
    }
}