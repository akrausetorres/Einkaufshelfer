package de.hsba.bi.einkaufshelfer.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    List<User> findByRole(String role);
    List<User> findByNameContains(String username);
}
