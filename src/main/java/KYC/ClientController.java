package KYC;

import KYC.person.Client;
import KYC.init.ClientService;
import KYC.init.UserService;
import KYC.person.User;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import kyc.dao.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private ClientService clientService;
    
      // Return registration form template
        @RequestMapping(value = "/addclient", method = RequestMethod.GET)
        public ModelAndView showAddClientPage
        (ModelAndView modelAndView, Client client) {
            modelAndView.addObject("client", client);
            modelAndView.setViewName("addclient");
            return modelAndView;
        }

   
               // Process form input data
        @RequestMapping(value = "/addclient", method = RequestMethod.POST)
        public ModelAndView processClientForm(ModelAndView modelAndView, @Valid Client client, BindingResult bindingResult, HttpServletRequest request, Authentication authentication) {

            // Lookup client 
            Client clientExists = clientService.findByClientname(client.getClientname());
           
            
            
            if (clientExists != null) {
                modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a client registered with the email provided.");
                modelAndView.setViewName("addclient");
                bindingResult.reject("client");
            }

            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("addclient");
            } else { 

                User user = userService.findByUsername(authentication.getName());

                //work on this segment
                client.setUser(user); 

                clientService.add(client); //check this out at some point 
                modelAndView.setViewName("clientadded");

            }
            return modelAndView;
        }

    
    }

