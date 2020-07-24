package de.hsba.bi.einkaufshelfer.user;

import de.hsba.bi.einkaufshelfer.address.Address;
import de.hsba.bi.einkaufshelfer.rating.Rating;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements Comparable<User> {

    public static final String HELPER_ROLE = "HELPER";
    public static final String NEEDY_ROLE = "NEEDY";
    public static final String BOOTH_ROLE = "BOOTH";

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "toUser")
    @OrderBy
    private List<Rating> ratings;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private Address address;

    private String role;

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;

        //make sure role is not manipulated, in case we give BOOTH
        this.role = role == HELPER_ROLE ? HELPER_ROLE : role == NEEDY_ROLE ? NEEDY_ROLE : role == BOOTH_ROLE ? BOOTH_ROLE : BOOTH_ROLE;
    }

    public String getTranslatedRole() {
        switch (this.role) {
            case HELPER_ROLE:
                return "Helfer";
            case NEEDY_ROLE:
                return "Hilfbedürftiger";
            default:
                return "Helfer & Hilfsbedürftiger";
        }
    }

    public BigDecimal calculateRating() {
        List<Rating> ratings = this.ratings;
        int sumRatings = 0;

        for (Rating rating : ratings) {
            sumRatings += rating.getStars();
        }

        return ratings.size() > 0 ? new BigDecimal(sumRatings).divide(new BigDecimal(ratings.size()), 1, RoundingMode.HALF_EVEN) : null;
    }

    public static String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return null;
    }

    @Override
    public int compareTo(User other) {
        return this.name.compareTo(other.name);
    }
}
