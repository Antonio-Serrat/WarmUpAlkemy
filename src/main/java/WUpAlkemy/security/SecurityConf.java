package WUpAlkemy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import WUpAlkemy.service.UserDetailsIm;

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsIm userDetailsService;

	@Bean
	public BCryptPasswordEncoder passEncode() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/GET/**", "/POST/auth/**").permitAll()
				.antMatchers("/POST/posts", "/PATCH/**").hasRole("USER").antMatchers("/DELETE/**").hasAnyRole("ADMIN")
				.anyRequest().authenticated().and().formLogin().permitAll().and().logout().logoutSuccessUrl("/");

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		try {
			auth.userDetailsService(userDetailsService).passwordEncoder(passEncode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
