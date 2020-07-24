package de.hsba.bi.einkaufshelfer.address;

import de.hsba.bi.einkaufshelfer.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;

    public void deleteAddress(Address a) {
        repository.delete(a);
    }

    public Address saveAddress(Address a) {
        return repository.save(a);
    }

    //TODO: Edit this method to get HelpRequests of User
    public List<User> getUsersFromCity(String city) {
        List<Address> addresses = repository.findAllByCity(city);

        List<User> users = new ArrayList();

        for(Address a : addresses) {
            users.add(a.getUser());
        }

        return users;
    }
}
