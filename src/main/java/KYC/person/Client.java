/*
 * https://hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp/
 */
package KYC.person;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "clients")
public class Client {
        private String username;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientId;
    
    @OneToMany
    @JoinTable(name = "client_name", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "client_id"))
    // check if these are required??

    private String clientname;
    private Set<Client> client;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

     public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public Set<Client> getClients(){
        return client;
    }

    public void setClients(Set<Client> clients) {
        this.client = clients;
    }
}
