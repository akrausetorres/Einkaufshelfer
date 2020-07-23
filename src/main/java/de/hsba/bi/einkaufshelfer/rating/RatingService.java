package de.hsba.bi.einkaufshelfer.rating;

import de.hsba.bi.einkaufshelfer.user.User;
import de.hsba.bi.einkaufshelfer.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository repository;
    private final UserService userService;

    public Rating submitRating(Integer stars, User toUser) {
        User currentUser = userService.findCurrentUser();
        //TODO: Check if the users are partners -> one helps the other
        return repository.save( new Rating(currentUser, toUser, stars) );
    }

    public Rating getRating(User fromUser, User toUser) {
        return repository.findByFromUserAndToUser(fromUser, toUser);
    }

    public void deleteRating(Rating rating) {
        repository.delete(rating);
    }

    public Rating updateRating(Rating rating) {
        return repository.save(rating);
    }

    public BigDecimal calculateUserRating(User user) {
        List<Rating> ratings = repository.findByToUser(user);

        int sumRatings = 0;

        for (Rating rating : ratings) {
            sumRatings += rating.getStars();
        }

        return new BigDecimal(sumRatings / ratings.size());
    }
}
