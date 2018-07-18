package kyc.dao;

import KYC.person.Client;
import KYC.person.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByClientname(String clientname);

    public void add(Client client);
}
