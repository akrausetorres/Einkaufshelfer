package de.hsba.bi.einkaufshelfer.address;

import de.hsba.bi.einkaufshelfer.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByCity(String city);
}