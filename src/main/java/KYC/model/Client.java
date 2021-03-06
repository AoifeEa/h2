/*
 * author Aoife Earl
 * some code references: 
 * https://hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp/
 *
 * http://websystique.com/springmvc/spring-mvc-4-fileupload-download-hibernate-example/ (file upload, relationship between client & dbfile) 
 * @ 30th July 2018
 * mapping ref: https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
 * @ 19th July 2018 
 */
package KYC.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientId;

    private String clientname;

    private String clienttype;

    private String country;

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

    public String getClienttype() {
        return clienttype;
    }

    public void setClienttype(String clienttype) {
        this.clienttype = clienttype;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<DBFile> userDBFile = new HashSet<DBFile>();

    public Set<DBFile> getUserDBFile() {
        return userDBFile;
    }

    public void setUserDBFile(Set<DBFile> userDBFile) {
        this.userDBFile = userDBFile;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OnDelete(action = OnDeleteAction.CASCADE)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}