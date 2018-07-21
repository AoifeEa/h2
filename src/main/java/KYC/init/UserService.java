package KYC.init;

import KYC.person.User;


public interface UserService {
    void save(User user);
    
    User findByUsername(String username);
    
}
