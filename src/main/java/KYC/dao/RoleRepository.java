/*
@24/06/2018 * and open the template in the editor.
 */
package kyc.dao;

import KYC.person.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{

    public Role findByName(String admin);
}