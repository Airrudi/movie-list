package nl.ruudclaassen.movie_list.config;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import nl.ruudclaassen.movie_list.service.LoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{		
		auth.userDetailsService(loginService);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().hasRole("USER")
				.and()
			.formLogin()
				.loginPage("/login/")
				.permitAll()
				.successHandler(loginSuccessHandler())
				.failureHandler(loginFailureHandler())
				.and()
			.logout()
				.permitAll()
				.logoutSuccessUrl("/login/");
	}
	
	public AuthenticationSuccessHandler loginSuccessHandler(){
		return (request, response, authentication) -> response.sendRedirect("/loggedIn/");
	}
	
	public AuthenticationFailureHandler loginFailureHandler(){
		return (request, response, authentication) -> {
			// TODO: Add flashmessages?
			//request.getSession().setAttribute("flash", new FlashMessage("Incorrect username / password combination", FlashMessage.Status.FAILURE));
			response.sendRedirect("/login/");
		};
	}
	
	
	
	

}
