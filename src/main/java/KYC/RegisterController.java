/*
 * Reference: https://www.codebyamir.com/blog/user-account-registration-with-spring-boot
 * @ 15th of July 2018
 * Reference: https://spring.io/guides/gs/validating-form-input/
 * @ 18th of July 2018 
 */
package KYC;

import KYC.init.UserService;
import KYC.person.Role;
import KYC.person.User;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import kyc.dao.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserService userService;

    // Return registration form template
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    // Process form input data
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request) {

        // Lookup user 
        User userExists = userService.findByUsername(user.getUsername());
        
        if (userExists != null) {
            modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
            modelAndView.setViewName("register");
            bindingResult.reject("user");
        }
        
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("register");
        } else { // new user so we create user with default role of "user"

            Role r1 = roleRepository.findByName("User");
            
            Set<Role> role = new HashSet<>();
            role.add(r1);
            user.setRoles(role);
            
            userService.save(user);
            modelAndView.setViewName("registered");
        }
        return modelAndView;
    }
}
