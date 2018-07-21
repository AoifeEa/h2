/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KYC.init;

import KYC.person.Client;
import KYC.person.Role;
import KYC.person.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import kyc.dao.ClientRepository;
import kyc.dao.RoleRepository;
import kyc.dao.UserRepository;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
 
@Component
public class DataInit implements ApplicationRunner {
 
     @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
 
    @Autowired
    private UserService userService;

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ClientRepository clientRepository;
    
    
 
    @Override
    public void run(ApplicationArguments args) throws Exception {
         int count = 0;
         if (count == 0) {
            User user1 = new User();
            Role r1 = new Role();
            Role r2 = new Role();
            r1.setName("Admin");
            r2.setName("User");
            
            roleRepository.save(r1);
            roleRepository.save(r2);
            Set<Role> roles = new HashSet();
            roles.add(r1);
            roles.add(r2);
            user1.setRoles(roles);
            user1.setUsername("Aoife");
            user1.setPassword("abc");
            
         userService.save(user1);
         
      Client client = new Client(); 
            client.setClientname("abc.ltd");
            client.setUser(user1);
            Client client1 = new Client(); 
            client1.setClientname("xyz.ltd");
            client1.setUser(user1);
            Client client2 = new Client(); 
            client2.setClientname("123.ltd");
            client2.setUser(user1);
            
            clientRepository.save(client);
            clientRepository.save(client1);
            clientRepository.save(client2);
            
         
        }
 
    }
    
   
}
     
