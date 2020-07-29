package de.hsba.bi.einkaufshelfer.helpoffer;

import de.hsba.bi.einkaufshelfer.helprequest.HelpRequest;
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
public class HelpOffer {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(optional = false)
    private HelpRequest helpRequest;

    @ManyToOne(optional = false)
    private User fromUser;

    @Basic(optional = false)
    private Boolean isAccepted;

    @Basic(optional = true)
    private BigDecimal budgetOffer;

    @Basic(optional = true)
    private String message;

}
