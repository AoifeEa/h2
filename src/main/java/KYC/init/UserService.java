package KYC.init;

import KYC.model.User;


public interface UserService {
    void save(User user);
    
    User findByUsername(String username);
    
}
