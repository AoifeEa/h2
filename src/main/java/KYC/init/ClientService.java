package KYC.init;

import KYC.person.Client;
import KYC.person.User;


public interface ClientService {

    void add(Client client);
    
    
    Client findByClientname(String clientname);
    
}
