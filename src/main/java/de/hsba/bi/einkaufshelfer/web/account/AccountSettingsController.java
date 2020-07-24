package de.hsba.bi.einkaufshelfer.web.account;

import de.hsba.bi.einkaufshelfer.address.Address;
import de.hsba.bi.einkaufshelfer.address.AddressService;
import de.hsba.bi.einkaufshelfer.user.User;
import de.hsba.bi.einkaufshelfer.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/account/settings")
@RequiredArgsConstructor
public class AccountSettingsController {

    private final UserService userService;
    private final AddressService addressService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String settings(Model model) {
        if (userService.findCurrentUser() == null) {
            return "redirect:/account/login";
        }
        return "account/settings";
    }

    @ModelAttribute("pageTitle")
    public String setTitle() {
        return "Mein Konto";
    }

    @ModelAttribute("pageDescription")
    public String setDesc() {
        return "Verwalte dein Konto";
    }


    @ModelAttribute("loggedInUser")
    public User getCurrentUser() {
        return userService.findCurrentUser();
    }

    @ModelAttribute("editAddressForm")
    public EditAddressForm getAddressForm() {
        User user = userService.findCurrentUser();
        EditAddressForm addressForm = new EditAddressForm();
        if(user == null) {return addressForm;}
        if (user.getRole().equals("NEEDY") || user.getRole().equals("BOOTH")) {
            if (!user.getAddress().getStreet().isEmpty()) {
                addressForm.setStreet(user.getAddress().getStreet());
                addressForm.setStreetNr(user.getAddress().getStreetNr());
                addressForm.setPostalcode(user.getAddress().getPostalcode());
                addressForm.setCity(user.getAddress().getCity());
                addressForm.setCountry(user.getAddress().getCountry());
            }
        }
        return addressForm;
    }

    @ModelAttribute("editUserForm")
    public EditUserForm getUserForm() {
        User user = userService.findCurrentUser();
        EditUserForm userForm = new EditUserForm();
        if(user == null) {return userForm;}
        userForm.setRole(user.getRole());
        userForm.setUsername(user.getName());
        return  userForm;
    }

    @ModelAttribute("passwordChangeForm")
    public PasswordChangeForm getPasswordChangeForm() {
        return new PasswordChangeForm();
    }

    @PostMapping("/address")
    public String editAddress(
            Model model,
            @ModelAttribute("editAddressForm") @Valid EditAddressForm form, BindingResult addressResult) {
        if (addressResult.hasErrors()) {
            return "account/settings";
        }

        User user = userService.findCurrentUser();

        String street = form.getStreet();
        String streetNr = form.getStreetNr();
        String postalcode = form.getPostalcode();
        String city = form.getCity();
        String country = form.getCountry();

        user.setAddress(new Address(user, street, streetNr, postalcode, city, country));
        userService.saveUser(user);

        model.addAttribute("editAddressSuccess", "Die Adresse wurde erfolgreich geändert.");
        return "/account/settings";
    }

    @PostMapping("/user")
    public String editUser(
            Model model,
            @ModelAttribute("editUserForm") @Valid EditUserForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "account/settings";
        }

        User user = userService.findCurrentUser();

        //make sure role is not manipulated, in case we give BOOTH
        String role = form.getRole().equals(User.HELPER_ROLE) ? User.HELPER_ROLE : form.getRole().equals(User.NEEDY_ROLE) ? User.NEEDY_ROLE : form.getRole().equals(User.BOOTH_ROLE) ? User.BOOTH_ROLE : User.BOOTH_ROLE;
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

    @PostMapping("password-change")
    public String passwordChange(
            Model model,
            @ModelAttribute("passwordChangeForm") @Valid PasswordChangeForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "account/settings";
        }

        String oldPassword = form.getOldPassword();
        String password = form.getNewPassword();
        String passwordConfirm = form.getNewPasswordConfirm();

        User user = userService.findCurrentUser();

        if (!password.equals(passwordConfirm)) {
            model.addAttribute("passwordChangeError", "Die beiden Passwörter stimmen nicht überein. Bitte erneut versuchen.");
            return "account/settings";
        }

        if ( !passwordEncoder.matches(oldPassword, user.getPassword()) ) {
            model.addAttribute("passwordChangeError", "Dein aktuelles Passwort ist nicht korrekt.");
            return "account/settings";
        }

        user.setPassword(passwordEncoder.encode(password));
        userService.saveUser(user);

        model.addAttribute("passwordChangeSuccess", "Das Passwort wurde erfolgreich geändert.");
        return "/account/settings";
    }
}
