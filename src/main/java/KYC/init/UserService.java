/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KYC.init;

import KYC.person.User;


public interface UserService {
    void save(User user);
    
    User findByUsername(String username);
    
}
