package WUpAlkemy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors().and().csrf()
				.disable().authorizeRequests().antMatchers(HttpMethod.POST, "/", "/login", "/auth/**").permitAll().and()
				.authorizeRequests().antMatchers(HttpMethod.GET, "/view/**").permitAll().and().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/view/posts").hasAnyRole("ROLE_USER").and().authorizeRequests()
				.antMatchers(HttpMethod.DELETE, "/view/posts/:id").hasAnyRole("ROLE_USER").and().authorizeRequests()
				.antMatchers(HttpMethod.PATCH, "/view/posts/:id").hasAnyRole("ROLE_USER").anyRequest().authenticated()
				.and().addFilter(new JWTAuthen(authenticationManager()))
				.addFilter(new JWTAuthorize(authenticationManager()));

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		try {
			auth.userDetailsService(userDetailsService).passwordEncoder(passEncode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}
