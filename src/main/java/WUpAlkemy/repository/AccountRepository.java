package WUpAlkemy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import WUpAlkemy.entities.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

	Account findByUsername(String username);

}
