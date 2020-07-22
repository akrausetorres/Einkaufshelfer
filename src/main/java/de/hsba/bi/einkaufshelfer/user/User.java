package de.hsba.bi.einkaufshelfer.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
