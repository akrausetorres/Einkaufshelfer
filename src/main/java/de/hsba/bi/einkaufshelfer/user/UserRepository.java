package de.hsba.bi.einkaufshelfer.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    List<User> findByRole(String role);
}
