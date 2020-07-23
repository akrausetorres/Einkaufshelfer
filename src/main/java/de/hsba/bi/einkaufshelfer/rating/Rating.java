package de.hsba.bi.einkaufshelfer.rating;

import de.hsba.bi.einkaufshelfer.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToOne(optional = false)
    private User fromUser;

    @OneToOne(optional = false)
    private User toUser;

    @Basic(optional = false)
    private Integer stars;

    public Rating(User fromUser, User toUser, Integer stars) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.stars = stars;
    }
}

