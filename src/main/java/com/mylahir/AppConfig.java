package com.mylahir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mylahir.jwtfilter.JwtRequestFilter;


@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService myUserDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers("/authenticate","/registration","/create-user").permitAll().
						anyRequest().authenticated().and().
						exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

}
/*
 * @Configuration
 * 
 * @EnableWebSecurity public class AppConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Autowired private UserDetailsService myUserDetailsService;
 * 
 * @Autowired private JwtRequestFilter jwtRequestFilter;
 * 
 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
 * throws Exception { auth.userDetailsService(myUserDetailsService);
 * 
 * 
 * }
 * 
 * @Bean public PasswordEncoder passwordEncoder() { return
 * NoOpPasswordEncoder.getInstance(); }
 * 
 * @Bean public AuthenticationProvider provider() {
 * 
 * DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
 * provider.setUserDetailsService(myUserDetailsService);
 * //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
 * //provider.setPasswordEncoder(new BCryptPasswordEncoder()); return provider;
 * 
 * }
 * 
 * @Override
 * 
 * @Bean public AuthenticationManager authenticationManagerBean() throws
 * Exception { return super.authenticationManagerBean(); }
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * 
 * http.csrf().disable() .authorizeRequests().antMatchers("/authenticated")
 * .permitAll() .anyRequest().authenticated().and().
 * exceptionHandling().and().sessionManagement()
 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
 * 
 * http.addFilterBefore(jwtRequestFilter,
 * UsernamePasswordAuthenticationFilter.class);
 */
		
//.and()
//.formLogin().loginPage("/login")
//.permitAll().and()
//.logout().invalidateHttpSession(true).clearAuthentication(true)
//.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/logout-done")
//.permitAll();

//		http
//		.authorizeRequests()
//		.antMatchers("/login").permitAll()
//		.and().authorizeRequests().antMatchers("/home").permitAll()
//		.and()
//		.authorizeRequests()
//		.antMatchers("/registration").anonymous()
//		.antMatchers("/create-user").anonymous()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin()
//		.loginPage("/login")
//		.permitAll()
//		.and()
//		.logout().invalidateHttpSession(true).clearAuthentication(true)
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/logout-done")
//		.permitAll();		

//		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/welcome").hasAnyRole("USER", "ADMIN")
//				.antMatchers("/getEmployees").hasAnyRole("USER", "ADMIN").antMatchers("/addNewEmployee")
//				.hasAnyRole("ADMIN").anyRequest().authenticated()
//				.and().formLogin().loginPage("/login").permitAll()
//				.and().logout().permitAll();

//	}
//}
