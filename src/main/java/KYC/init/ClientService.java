package KYC.init;

import KYC.model.Client;
import KYC.model.User;
import java.util.List;


public interface ClientService {

    void add(Client client);
    
    Client findByClientname(String clientname);
    List<Client> findByuser_id(User user);

    List<Client> findAll();
    
}
