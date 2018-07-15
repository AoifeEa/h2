/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KYC;


import KYC.init.UserService;
import KYC.person.Role;
import KYC.person.User;
import java.util.HashSet;
import java.util.Set;
import kyc.dao.RoleRepository;
import kyc.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String index() {
        User userExists = userService.findByUsername("Efa");
        if (userExists == null) {

            User user1 = new User();
            user1.setUsername("Efa");
            user1.setPassword("123");
            Role r1 = roleRepository.findByName("Admin");

            Set<Role> role = new HashSet<>();
            role.add(r1);
            user1.setRoles(role);

            userService.save(user1);
        }
        Iterable<User> all = userRepository.findAll();

        StringBuilder sb = new StringBuilder();

        for (User user : all) {
            sb.append("name:").append(user.getUsername()).append("<br>");
            user.getRoles().forEach((role) -> {
                sb.append("Role:").append(role.getName()).append("<br>");
            });
        }
        return sb.toString();

    }
  @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return "Name: " + authentication.getName() +" Permissions: " + authentication.getAuthorities().toString() + " athenticated: " + authentication.isAuthenticated();
    }}