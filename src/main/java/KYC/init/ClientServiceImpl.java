package KYC.init;

import KYC.person.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kyc.dao.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    
    public void add(Client client) { //should this just be save instead of saveUser
        clientRepository.add(client);
    }

    @Override
    public Client findByClientname(String clientname) {
        return clientRepository.findByClientname(clientname);
    }
}
