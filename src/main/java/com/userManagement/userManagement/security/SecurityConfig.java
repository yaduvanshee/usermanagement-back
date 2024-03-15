package com.userManagement.userManagement.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtAuthenticationEntryPoint point;

  /**
   * Provides a bean for configuring the security filter chain. The security filter chain defines
   * the security configuration for HTTP requests, including CSRF protection, CORS policy, request
   * authorization rules, and HTTP basic authentication.
   *
   * @param http The {@link HttpSecurity} object used to configure security settings for HTTP
   *             requests.
   * @return A {@link SecurityFilterChain} instance representing the configured security filter
   * chain for the application.
   * @throws Exception If an error occurs during the security configuration.
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .cors(cors -> cors.disable())
        .authenticationProvider(daoAuthenticationProvider())
        .authorizeHttpRequests(auth ->
            auth.requestMatchers(HttpMethod.POST, "/user/create/**").permitAll()
                .anyRequest().authenticated())
        .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
        .httpBasic(withDefaults())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    return http.build();
  }

  /**
   * Provides a bean for creating an in-memory user details service.
   * The user details service is responsible for managing user information
   * such as usernames, passwords, and roles, and is used for authentication
   * and authorization purposes within the application.
   *
   * @return An {@link InMemoryUserDetailsManager} instance containing
   * two user details objects: one for an admin user with the username "saurabh"
   * and password "123", and another for an admin user with the username "saur"
   * and password "12345". Passwords are encoded using the configured
   * {@link PasswordEncoder} bean to ensure security.
   */
//  @Bean
//  public UserDetailsService userDetailsService() {
//    UserDetails admin = User.builder()
//        .username("saurabh")
//        .password(passwordEncoder().encode("123"))
//        .build();
//    UserDetails admin1 = User.builder()
//        .username("saur")
//        .password(passwordEncoder().encode("12345"))
//        .build();
//    return new InMemoryUserDetailsManager(admin, admin1);
//  }

  /**
   * Provides a bean for creating instances of a password encoder. The password encoder is used for
   * encoding and verifying passwords in a secure manner.
   *
   * @return A {@link PasswordEncoder} instance that uses the BCrypt hashing algorithm for password
   * encoding.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Provides a bean for creating a DaoAuthenticationProvider, which is a pre-defined
   * AuthenticationProvider used to authenticate users from a database.
   *
   * The DaoAuthenticationProvider relies on a user details service and a password encoder
   * to authenticate users securely. We configure this provider by registering our custom
   * user details service and password encoder.
   *
   * @return A configured instance of DaoAuthenticationProvider ready for use in authenticating
   * users against a database.
   */
  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }
}
