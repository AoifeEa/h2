package KYC.init;

import KYC.person.Client;
import KYC.person.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import KYC.dao.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    
        @Override
    public void add(Client client) {
           clientRepository.save(client);
    }

    @Override
    public Client findByClientname(String clientname) {
        return clientRepository.findByClientname(clientname);
    }


    @Override
    public List<Client> findByuser_id(User user) {
     return clientRepository.findAllByuser_id(user.getId());
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
        }
}
