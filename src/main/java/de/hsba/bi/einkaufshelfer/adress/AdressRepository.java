package de.hsba.bi.einkaufshelfer.adress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AdressRepository extends JpaRepository<Adress, Long> {

}
