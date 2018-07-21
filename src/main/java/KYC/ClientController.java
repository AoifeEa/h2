package KYC;

import KYC.person.Client;
import KYC.init.ClientService;
import javax.servlet.http.HttpServletRequest;
import kyc.dao.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private ClientService clientService;
    
        // Return new client form template
    @RequestMapping(value = "/myclients", method = RequestMethod.GET)
    public ModelAndView showAddClientPage(ModelAndView modelAndView, Authentication authentication) {
        Object client = null; //had to add this in to avoic error message ???
        modelAndView.addObject("client", client);
        modelAndView.setViewName("myclients");
        return modelAndView;
    }

   
               // Process form input data
        @RequestMapping(value = "/addclient", method = RequestMethod.POST)
        public ModelAndView processClientForm(ModelAndView modelAndView, @Valid Client client, BindingResult bindingResult, HttpServletRequest request) {

            // Lookup client 
            Client clientExists = clientService.findByClientname(client.getClientname());

            if (clientExists != null) {
                modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
                modelAndView.setViewName("addclient");
                bindingResult.reject("client");
            }

            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("addclient");
            } else { // new user so we create user and send confirmation e-mail

                Client c1 = clientRepository.findByClientname("Client");

                //work on this segment
                client.setUser("Aoife"); //mess around here

                clientService.add(client); //check this out at some point 
//            modelAndView.setViewName("clientadded");
            }
            return modelAndView;
        }

    
    }

