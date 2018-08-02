/*
 * author Aoife Earl
 *  *ref: https://www.callicoder.com/spring-boot-file-upload-download-jpa-hibernate-mysql-database-example/
 * @ 29th July 2018
 */
 

package KYC.dao;

import KYC.model.Client;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByClientname(String clientname);
    List<Client> findAllByuser_id(Long user_id);
}
