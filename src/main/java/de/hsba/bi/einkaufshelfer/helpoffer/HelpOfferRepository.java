package de.hsba.bi.einkaufshelfer.helpoffer;

import de.hsba.bi.einkaufshelfer.address.Address;
import de.hsba.bi.einkaufshelfer.helprequest.HelpRequest;
import de.hsba.bi.einkaufshelfer.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface HelpOfferRepository extends JpaRepository<HelpOffer, Long> {
    List<HelpOffer> findAllByHelpRequest(HelpRequest request);
    HelpOffer findByHelpRequestAndIsAccepted(HelpRequest request, Boolean isAccepted);
    List<HelpOffer> findAllByFromUser(User fromUser);
}