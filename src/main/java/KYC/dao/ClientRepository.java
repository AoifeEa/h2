package kyc.dao;

import KYC.person.Client;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByClientname(String clientname);
    List<Client> findAllByuser_id(Long user_id);
}
