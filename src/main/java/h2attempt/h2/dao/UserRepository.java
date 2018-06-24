/*
https://hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp/ * To change this template file, choose Tools | Templates
@24/06/2018
*/
package h2attempt.h2.dao;

import h2attempt.h2.person.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
