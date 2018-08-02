/*
 * author Aoife Earl
 * code reference
 *  *ref: https://www.callicoder.com/spring-boot-file-upload-download-jpa-hibernate-mysql-database-example/
 * @ 29th July 2018
 */
package KYC.dao;

import KYC.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

}