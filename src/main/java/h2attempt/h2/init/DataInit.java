/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package h2attempt.h2.init;

import h2attempt.h2.person.Person;
import h2attempt.h2.dao.PersonDAO;
import h2attempt.h2.person.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
 
@Component
public class DataInit implements ApplicationRunner {
 
    private PersonDAO personDAO;
 
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 
    @Autowired
    private UserService userService;

    
    @Autowired
    public DataInit(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    
 
    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = personDAO.count();
 
        if (count == 0) {
            Person p1 = new Person();
            User user1 = new User();
            user1.setUsername("Aoife");
            user1.setPassword("yellow7");
            userService.save(user1);
            
            
            p1.setFullName("John");
 
            Date d1 = df.parse("1980-12-20");
            p1.setDateOfBirth(d1);
            //
            Person p2 = new Person();
 
            p2.setFullName("Smith");
            Date d2 = df.parse("1985-11-11");
            p2.setDateOfBirth(d2);
 
            personDAO.save(p1);
            personDAO.save(p2);
        }
 
    }
    
   
}
     
