package KYC.init;

import KYC.person.Client;
import KYC.person.User;
import java.util.List;


public interface ClientService {

    void add(Client client);
    
    
    Client findByClientname(String clientname);
    List<Client> findByuser_id(User user);
    
}
