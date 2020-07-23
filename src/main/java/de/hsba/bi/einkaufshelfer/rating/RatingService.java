package de.hsba.bi.einkaufshelfer.rating;

import de.hsba.bi.einkaufshelfer.user.User;
import de.hsba.bi.einkaufshelfer.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository repository;

    public Rating getRating(User fromUser, User toUser) {
        return repository.findByFromUserAndToUser(fromUser, toUser);
    }

    public void deleteRating(Rating rating) {
        repository.delete(rating);
    }

    public Rating saveRating(Rating rating) {
        return repository.save(rating);
    }
}
