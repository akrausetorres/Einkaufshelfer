package de.hsba.bi.einkaufshelfer.user;

import java.util.List;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationStartedEvent.class)
    public void init() {
        createUser("helper", "Start123", User.HELPER_ROLE);
        createUser("needy", "Pwd123", User.NEEDY_ROLE);
    }

    private void createUser(String name, String password, String role) {
        userRepository.save(new User(name, passwordEncoder.encode(password), role));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findHelpers() {
        return userRepository.findByRole(User.HELPER_ROLE);
    }

    public List<User> findNeeders() {
        return userRepository.findByRole(User.NEEDY_ROLE);
    }

    public User findCurrentUser() {
        return userRepository.findByName(User.getCurrentUsername());
    }
}
