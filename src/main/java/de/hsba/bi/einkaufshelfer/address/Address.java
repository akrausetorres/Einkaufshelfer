package de.hsba.bi.einkaufshelfer.address;

import de.hsba.bi.einkaufshelfer.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    // One user can have only one address - in future it makes more sense if user is able to add different addresses for every help request
    // In this application we simplify the address usage.
    @OneToOne(optional = false)
    private User user;

    @Basic(optional = false)
    private String street;

    //String because nr may have a letter - eg. 12a
    @Basic(optional = false)
    private String streetNr;

    @Basic(optional = false)
    private String postalcode;

    @Basic(optional = false)
    private String city;

    @Basic(optional = false)
    private String country;

    public Address(User user, String street, String streetNr, String postalcode, String city, String country) {
        this.user = user;
        this.street = street;
        this.streetNr = streetNr;
        this.postalcode = postalcode;
        this.city = city;
        this.country = country;
    }
}

