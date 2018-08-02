/*
 * author Aoife Earl 
*/

package KYC.service;

import KYC.model.User;


public interface UserService {
    void save(User user);
    
    User findByUsername(String username);
    
}
