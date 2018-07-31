/*
 *  *ref: https://www.callicoder.com/spring-boot-file-upload-download-jpa-hibernate-mysql-database-example/
* @ 29th July 2018
 */
package KYC.init;

import KYC.dao.DBFileRepository;
import KYC.person.Client;
import KYC.person.DBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;
    
    @Autowired
    private ClientService clientService;
    
    public DBFile storeFile(MultipartFile file, String clientName, String fileCategory) {
        DBFile dbFile = null;
        try {
            // Normalize file name
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Client client = clientService.findByClientname(clientName);
            dbFile = new DBFile(fileName, file.getContentType(), file.getBytes(), client, fileCategory);

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            Logger.getLogger(DBFileStorageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dbFile;
    }

    public Optional<DBFile> getFile(String fileId) {
        return dbFileRepository.findById(fileId);
    }
}
