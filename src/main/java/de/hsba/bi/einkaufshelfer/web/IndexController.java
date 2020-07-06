package de.hsba.bi.einkaufshelfer.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Hey!");
        model.addAttribute("pageDescription", "Hier ensteht dein neuer Einkaufshelfer!");
        return "layout";
    }
}
