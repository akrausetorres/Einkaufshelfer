package de.hsba.bi.einkaufshelfer.web.users;

import de.hsba.bi.einkaufshelfer.user.User;
import de.hsba.bi.einkaufshelfer.user.UserService;
import de.hsba.bi.einkaufshelfer.web.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("{username}")
    public String getUser(Model model, @PathVariable("username") String username) {
        User user = userService.findUser(username);

        if(user == null) {
            throw new NotFoundException();
        }

        model.addAttribute("user", user);

        return "users/user";
    }

}