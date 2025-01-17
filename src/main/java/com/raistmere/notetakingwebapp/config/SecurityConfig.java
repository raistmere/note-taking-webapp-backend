package com.raistmere.notetakingwebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // disable csrf (not sure if I need to do this or is it auto disabled)
        http.csrf(AbstractHttpConfigurer::disable);

        // cors Filter rules
        http.cors(cors -> {

            cors.configurationSource(corsConfigurationSource());
        });

        // authorize filter rules
        http.authorizeHttpRequests(authReq -> {

            authReq.requestMatchers("/", "/login").permitAll();
            authReq.requestMatchers("/dashboard").authenticated();
        });

        // formLogin filter rules
        http.formLogin(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        // create cors rules
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        // map those rules to specific urls (in this case it's applied to all urls)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public UserDetailsService userDetailsService() {

        // create an inMemory manager that keeps track of all users (good for testing/dev stage)
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // create a new user object that will hold the test user data for development use
        User newUser = new User(
                "user",
                "$2a$12$b9Hk7u7x68TNA4RTYgS8ZO9P1pzbCIBpHOc8.prBbpsF5nN6cdJwi",
                true,
                true,
                true,
                true,
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );

        // add that user to the inMemory manager so we can use it
        manager.createUser(newUser);

        // now we can use the manager and login using the new user(s).
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


}
