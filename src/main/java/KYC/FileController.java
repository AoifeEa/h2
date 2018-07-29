// *ref: https://www.callicoder.com/spring-boot-file-upload-download-jpa-hibernate-mysql-database-example/
//* @ 29th July 2018

package KYC;

import KYC.init.ClientService;
import KYC.init.DBFileStorageService;
import KYC.init.UserService;
import KYC.person.Client;
import KYC.person.DBFile;
import KYC.person.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;



@Controller
public class FileController {


    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;
    @Autowired
    private DBFileStorageService DBFileStorageService;
    
    
    @GetMapping("/upload")
    public ModelAndView showAddClientPage(ModelAndView modelAndView, DBFile dbfile, Authentication authentication) {
        modelAndView.addObject("dbfile", dbfile);
        modelAndView.setViewName("upload");
        
            User user = userService.findByUsername(authentication.getName());
            List<Client> clients = clientService.findByuser_id(user);
            modelAndView.addObject("clients", clients);
        
        return modelAndView;
    }

    @PostMapping("/upload")
    public DBFile upload(@RequestParam MultipartFile file, @RequestParam String clientName, @RequestParam String fileCategory) {

        DBFile finalFile = DBFileStorageService.storeFile(file, clientName, fileCategory);
       
        return finalFile;
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        Optional<DBFile> dbFile = DBFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.get().getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.get().getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.get().getData()));
    }
}
