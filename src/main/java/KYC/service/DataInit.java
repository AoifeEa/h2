/*
 * author Aoife Earl
 */
package KYC.service;

import KYC.model.Client;
import KYC.model.Role;
import KYC.model.User;
import java.util.HashSet;
import java.util.Set;
import KYC.dao.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
       //search for user "Aoife"
        User userExists = userService.findByUsername("Aoife");
        //if user "Aoife" does not exist, create user with roles "user" & "admin"
        if (userExists== null) {
            User user1 = new User();
            Role r1 = new Role();
            Role r2 = new Role();
            r1.setName("Admin");
            r2.setName("User");

            roleRepository.save(r1);//save role admin
            roleRepository.save(r2);//save role user
            Set<Role> roles = new HashSet(); //initializing set of roles
            roles.add(r1);
            roles.add(r2);
            user1.setRoles(roles);//add roles to the "Aoife" user
            user1.setUsername("Aoife");
            user1.setPassword("abc");

            userService.save(user1);
            
            //initializing, defining and adding clients to user "Aoife" 
            Client client = new Client();
            client.setClientname("abc.ltd");
            client.setClienttype("Standard Ltd");
            client.setCountry("USA");
            client.setUser(user1);
            Client client1 = new Client();
            client1.setClientname("xyz.ltd");
            client1.setClienttype("Limited");
            client1.setCountry("UK");
            client1.setUser(user1);
            Client client2 = new Client();
            client2.setClientname("123.ltd");
            client2.setClienttype("Sub of Listed");
            client2.setCountry("Ireland");
            client2.setUser(user1);
            //saving clients to repository
            clientService.add(client);
            clientService.add(client1);
            clientService.add(client2);
            
           
           //user only example 
           Set<Role> roles1 = new HashSet();
            User user2 = new User();
            roles1.add(r2);
            user2.setRoles(roles1);
            user2.setUsername("Dave");
            user2.setPassword("123");

            userService.save(user2);

            Client client4 = new Client();
            client4.setClientname("Tom Murphy");
            client4.setClienttype("Private Individual");
            client4.setCountry("Ireland");
            client4.setUser(user2);
            Client client5 = new Client();
            client5.setClientname("Bob Limited");
            client5.setClienttype("Limited");
            client5.setCountry("UK");
            client5.setUser(user2);
            
            clientService.add(client4);
            clientService.add(client5);
            
        }
    }
}
