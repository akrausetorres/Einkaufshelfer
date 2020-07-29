package de.hsba.bi.einkaufshelfer.user;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import de.hsba.bi.einkaufshelfer.address.Address;
import de.hsba.bi.einkaufshelfer.address.AddressService;
import de.hsba.bi.einkaufshelfer.rating.Rating;
import de.hsba.bi.einkaufshelfer.rating.RatingService;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.thymeleaf.util.ListUtils;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RatingService ratingService;
    private final AddressService addressService;

    @EventListener(ApplicationStartedEvent.class)
    public void init() {
        //Create Demo Data
        User user1 = createUser("helper", "Start123", User.HELPER_ROLE, "Musterstraße", "12a", "20099", "Hamburg", "Deutschland");
        User user2 = createUser("needy", "Pwd123", User.NEEDY_ROLE, "Musterstraße", "11a", "20099", "Hamburg", "Deutschland");
        User user3 = createUser("john", "Pwd123", User.BOOTH_ROLE, "Musterstraße", "10a", "20099", "Hamburg", "Deutschland");

        ratingService.saveRating(new Rating(user1, user1, 4));
        ratingService.saveRating(new Rating(user2, user1, 5));
        ratingService.saveRating(new Rating(user3, user1, 1));
    }

    public User createUser(String name, String password, String role, String street, String streetNr, String postalcode, String city, String country) {
        User newUser = userRepository.save(new User(name.toLowerCase(), passwordEncoder.encode(password), role));
        newUser.setAddress(new Address(newUser, street, streetNr, postalcode, city, country));
        return newUser;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findHelpers() {
        List<User> helpersRole = new ArrayList<>(userRepository.findByRole(User.HELPER_ROLE));
        List<User> boothRole = userRepository.findByRole(User.BOOTH_ROLE);
        helpersRole.addAll(boothRole);
        return helpersRole;
    }

    public List<User> findNeeders() {
        List<User> needersRole = new ArrayList<>(userRepository.findByRole(User.NEEDY_ROLE));
        List<User> boothRole = userRepository.findByRole(User.BOOTH_ROLE);
        needersRole.addAll(boothRole);
        return needersRole;
    }

    public User findUser(String username) {
        return userRepository.findByName(username.toLowerCase());
    }

    public User findCurrentUser() {
        return userRepository.findByName(User.getCurrentUsername());
    }

    public Rating submitRating(Integer stars, User toUser) {
        User currentUser = findCurrentUser();
        return ratingService.saveRating( new Rating(currentUser, toUser, stars) );
    }
}
