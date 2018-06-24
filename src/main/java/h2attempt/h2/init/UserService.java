/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package h2attempt.h2.init;

import h2attempt.h2.person.User;


public interface UserService {
    void save(User user);
    
    User findByUsername(String username);
    
}
