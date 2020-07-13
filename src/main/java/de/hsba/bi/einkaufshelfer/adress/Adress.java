package de.hsba.bi.einkaufshelfer.adress;

import javax.persistence.*;

import lombok.*;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Adress {

    @Getter
    @Setter
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @Getter
    @Setter
    private User user;

    @Basic(optional = false)
    @Getter
    @Setter
    @Column(nullable = false)
    private String street;

    @Basic(optional = false)
    @Getter
    @Setter
    private String house_number;

    @ManyToOne(optional = false)
    @Getter
    @Setter
    private String postalcode;

    @ManyToOne(optional = false)
    @Getter
    @Setter
    private String district;

    @Basic
    @Getter
    @Setter
    private String bell_name;

    @Basic
    @Getter
    @Setter
    private  int floor_number;

    @Basic
    @Getter
    @Setter
    private Long additional_info;

}