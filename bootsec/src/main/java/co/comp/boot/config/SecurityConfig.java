package co.comp.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import co.comp.boot.security.CustomLoginSuccessHandler;

@Configuration
@EnableWebSecurity // security filter 설정과 같음
public class SecurityConfig{
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> 
		requests
			.antMatchers("/top","/login").permitAll()
			.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN") // boot에서는 ROLE_ 생략하고 그냥 ADMIN으로 적는것도 가능
			.anyRequest().authenticated())
		//.formLogin().loginPage("/login").usernameParameter("").and()
		.formLogin(login -> login.loginPage("/login")
								.usernameParameter("userId")
								.successHandler(successHandler()))
		.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/top")); //.deleteCookies(null) 가능
//			.csrf().disable();
		return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/css/**");
	}
}
