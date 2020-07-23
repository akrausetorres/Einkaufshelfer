package de.hsba.bi.einkaufshelfer.rating;

import de.hsba.bi.einkaufshelfer.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating findByFromUserAndToUser(User fromUser, User toUser);
    List<Rating> findByToUser(User user);
}