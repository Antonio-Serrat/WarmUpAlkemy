package WUpAlkemy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import WUpAlkemy.DTOs.AccountDTO;
import WUpAlkemy.Enum.Rol;
import WUpAlkemy.entities.Account;
import WUpAlkemy.entities.Blogger;
import WUpAlkemy.entities.Role;
import WUpAlkemy.service.AccountService;
import WUpAlkemy.service.BloggerService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	PasswordEncoder bcrypt;

	private AccountService serviceAcc;
	private BloggerService serviceBlgr;

	@Autowired
	public AuthController(AccountService serviceAcc, BloggerService serviceBlgr) {
		this.serviceAcc = serviceAcc;
		this.serviceBlgr = serviceBlgr;
	}

	@PostMapping(value = "/sign_up")
	@ResponseBody
	public void signUp(@RequestBody AccountDTO dto) {

		Account acc = new Account();
		Blogger blogger = new Blogger();
		Role user = new Role();

		user.setRol(Rol.USER);
		user.setAcc(acc);

		String pwd = bcrypt.encode(dto.getPass());
		acc.setUsername(dto.getEmail());
		acc.setPassword(pwd);
		acc.setRole(user);
		acc.setOwner(blogger);

		blogger.setAcc(acc);

		serviceAcc.save(acc);
		serviceAcc.saveRole(user);
		serviceBlgr.save(blogger);

	}

}
