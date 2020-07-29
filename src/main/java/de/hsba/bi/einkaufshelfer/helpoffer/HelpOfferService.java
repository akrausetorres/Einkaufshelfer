package de.hsba.bi.einkaufshelfer.helpoffer;

import de.hsba.bi.einkaufshelfer.helprequest.HelpRequest;
import de.hsba.bi.einkaufshelfer.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HelpOfferService {

    private final HelpOfferRepository repository;

    public Boolean deleteOffer(HelpOffer offer) {
        if (!offer.getIsAccepted()) {
            repository.delete(offer);
            return true;
        }
        return false;
    }

    public List<HelpOffer> getOffersForRequest(HelpRequest request) {
        return repository.findAllByHelpRequest(request);
    }

    public List<HelpOffer> getOffersOfUser(User user) {
        return repository.findAllByFromUser(user);
    }

    public HelpOffer getAcceptedOfferOfRequest(HelpRequest request) {
        return repository.findByHelpRequestAndIsAccepted(request, true);
    }
}
