package de.hsba.bi.einkaufshelfer.adress;

import javax.persistence.*;

import lombok.*;
//TODO Import and add User class


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Setter
public class Adress {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private Long id;

    /*@Basic(optional = false)
    private User user;*/

    @Basic(optional = false)
    private String street;

    @Basic(optional = false)
    private String house_number;

    @Basic(optional = false)
    private String postalcode;

    @Basic(optional = false)
    private String district;

    @Basic
    private String bell_name;

    @Basic
    private  int floor_number;

    @Basic
    private String additional_info;

    public Adress(String street, String house_number, String postalcode, String district, String bell_name, int floor_number, String additional_info){
        this.street = street;
        this.house_number = house_number;
        this.postalcode = postalcode;
        this.district = district;
        this.bell_name = bell_name;
        this.floor_number = floor_number;
        this.additional_info = additional_info;
    }

}
