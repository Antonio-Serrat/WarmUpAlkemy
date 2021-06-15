package WUpAlkemy.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/POST/auth")
public class AuthController {

	@PostMapping(value = "/sign_up")
	public void signUp() {

	}
}
