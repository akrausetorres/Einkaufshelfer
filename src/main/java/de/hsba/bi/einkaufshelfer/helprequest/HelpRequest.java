package de.hsba.bi.einkaufshelfer.helprequest;

import de.hsba.bi.einkaufshelfer.helpoffer.HelpOffer;
import de.hsba.bi.einkaufshelfer.rating.Rating;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class HelpRequest {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "helpRequest")
    @OrderBy
    private List<HelpOffer> helpOffers;

}
