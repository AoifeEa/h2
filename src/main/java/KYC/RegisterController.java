/*
 * Reference: https://www.codebyamir.com/blog/user-account-registration-with-spring-boot
 * @ 15th of July 2018
 */
package KYC;

import KYC.init.UserService;
import KYC.person.User;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

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

        System.out.println(userExists);

        if (userExists != null) {
            modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
            modelAndView.setViewName("register");
            bindingResult.reject("user");
        }

//		if (bindingResult.hasErrors()) { 
//			modelAndView.setViewName("register");		
//		} else { // new user so we create user and send confirmation e-mail
//					
//			// Disable user until they click on confirmation link in email
//		    user.setEnabled(false);
//		      
//		    userService.saveUser(user);
//				
//		return modelAndView;
//	}
//Is this segment required???
        // Process confirmation link
//	@RequestMapping(value="/confirm", method = RequestMethod.GET)
//	public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {
//			
//		User user = userService.findByConfirmationToken(token);
//			
//		if (user == null) { // No token found in DB
//			modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
//		} else { // Token found
//			modelAndView.addObject("confirmationToken", user.getConfirmationToken());
//		}
//			
//		modelAndView.setViewName("confirm");
//		return modelAndView;		
//	}
//	
	// Process confirmation link
//	@RequestMapping(value="/confirm", method = RequestMethod.POST)
//	public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map requestParams, RedirectAttributes redir) {
//				
//		modelAndView.setViewName("confirm");
//		
//		Zxcvbn passwordCheck = new Zxcvbn();
//		
//		Strength strength = passwordCheck.measure(requestParams.get("password"));
//		
//		if (strength.getScore() < 3) {
//			bindingResult.reject("password");
//			
//			redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");
//
//			modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
//			System.out.println(requestParams.get("token"));
//			return modelAndView;
//		}
//	
//		// Find the user associated with the reset token
//		User user = userService.findByConfirmationToken(requestParams.get("token"));
        // Set new password
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword ()));

        // Set user to enabled
        user.setEnabled(true);

        // Save user
        userService.save(user);

        modelAndView.addObject("successMessage", "Your password has been set!");
        return modelAndView;
    }

}
