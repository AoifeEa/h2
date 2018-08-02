/*
 * author Aoife Earl
 * code reference: https://hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp/ 
 * 24/06/2018
 */


package KYC.service;

import KYC.model.Client;
import KYC.model.User;
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
