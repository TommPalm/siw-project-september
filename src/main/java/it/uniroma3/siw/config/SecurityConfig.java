package it.uniroma3.siw.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	 @Bean
	    public AuthenticationSuccessHandler successHandler() {
	        return (request, response, authentication) -> {

	            if (authentication.getAuthorities().stream()
	                    .anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

	                response.sendRedirect("/admin/gestione");

	            } else {
	                response.sendRedirect("/registered/home");
	            }
	        };
	    }
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {

        JdbcUserDetailsManager manager =
                new JdbcUserDetailsManager(dataSource);

        manager.setUsersByUsernameQuery(
                "SELECT username, password, 1 AS enabled " +
                "FROM credenziali WHERE username = ?"
        );

        manager.setAuthoritiesByUsernameQuery(
                "SELECT username, user_role AS authority " +
                "FROM credenziali WHERE username = ?"
        );

        return manager;
    }
    
    @Bean
    protected SecurityFilterChain configure(
            final HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(authorize -> {

            authorize.requestMatchers(
                    "/","/images/**","/css/**","/festival/{id}/info","/film/{id}/info","/regista/{id}/info","/register"
            ).permitAll();

            authorize.requestMatchers("/admin/**")
                    .hasAuthority("ADMIN");

            authorize.requestMatchers("/registered/**")
                    .authenticated();

            authorize.anyRequest().authenticated();
        });

        httpSecurity.formLogin(form -> {
            form
                .loginPage("/login")
                .successHandler(successHandler())
                .permitAll();

            form.failureUrl("/login?error=true");
        });

        return httpSecurity.build();
    }
}

