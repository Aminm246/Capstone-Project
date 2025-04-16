package edu.metro.subscriptionshepard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // This creates a password encoder to securely store user passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection for H2 console only
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                AntPathRequestMatcher.antMatcher("/h2-console/**"))
                )

                // Configure which URLs are accessible without login
                .authorizeHttpRequests(auth -> {
                    // Allow access to H2 console and public pages
                    auth.requestMatchers(
                            AntPathRequestMatcher.antMatcher("/h2-console/**"),
                            AntPathRequestMatcher.antMatcher("/login"),
                            AntPathRequestMatcher.antMatcher("/register"),
                            AntPathRequestMatcher.antMatcher("/css/**"),
                            AntPathRequestMatcher.antMatcher("/js/**")
                    ).permitAll();

                    // Require login for all other pages
                    auth.anyRequest().authenticated();
                })

                // Set up the login page and redirect after successful login
                .formLogin(form -> form
                        .loginPage("/login")          // Custom login page URL
                        .defaultSuccessUrl("/dashboard") // Where users go after login
                        .permitAll()                  // Allow everyone to access login page
                )

                // Configure logout functionality
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout") // Redirect after logout
                        .permitAll()                     // Allow everyone to logout
                )

                // Allow H2 console to work in frames
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                );

        return http.build();
    }
}
