package WUpAlkemy.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import WUpAlkemy.entities.Account;
import WUpAlkemy.entities.Role;
import WUpAlkemy.repository.AccountRepository;

@Service
public class UserDetailsIm implements UserDetailsService {

	@Autowired
	private AccountRepository repoAc;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			Account userDb = repoAc.findByUsername(username);
//			Account user = userDb.get();
			List<Role> permis = new ArrayList<>();
			Role permissions = userDb.getRole();
			permis.add(permissions);
			Set<String> roles = new HashSet<>();
			if (!CollectionUtils.isEmpty(permis)) {
				for (Role p : permis) {
					roles.add(p.getRol().name());
				}
			}
			return User.withUsername(username).roles(roles.toArray(new String[0])).password(userDb.getPassword()).build();
		} catch (UsernameNotFoundException ex) {
			Account userDb = null;
			throw new UsernameNotFoundException(ex.getMessage() + "Can't find the user with name " + username + ".");
		}

	}
}
