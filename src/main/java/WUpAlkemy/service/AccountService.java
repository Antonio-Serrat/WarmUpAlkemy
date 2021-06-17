package WUpAlkemy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WUpAlkemy.entities.Account;
import WUpAlkemy.entities.Role;
import WUpAlkemy.repository.AccountRepository;
import WUpAlkemy.repository.RoleRepository;

@Service
public class AccountService {

	private AccountRepository accRepo;
	private RoleRepository roleRepo;

	@Autowired
	public AccountService(AccountRepository accRepo, RoleRepository roleRepo) {
		this.accRepo = accRepo;
		this.roleRepo = roleRepo;
	}

	public List<Account> findAll() {
		return (List<Account>) accRepo.findAll();
	}

	public Optional<Account> findById(Long id) {
		return accRepo.findById(id);
	}

	public void save(Account account) {
		accRepo.save(account);
	}

	public void deleteById(Long id) {
		accRepo.deleteById(id);
	}

	public List<Role> findAllRole() {
		return (List<Role>) roleRepo.findAll();
	}

	public Optional<Role> findRoleById(Long id) {
		return roleRepo.findById(id);
	}

	public void saveRole(Role role) {
		roleRepo.save(role);
	}

	public void deleteRoleById(Long id) {
		roleRepo.deleteById(id);
	}

}
