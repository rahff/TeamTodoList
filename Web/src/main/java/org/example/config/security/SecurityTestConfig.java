package org.example.config.security;

import org.example.security.jwt.JwtConfigurer;
import org.example.security.jwt.JwtFilter;
import org.example.security.testing.FakeTokenService;
import org.example.security.testing.JwtAuthenticationForTesting;
import org.example.security.testing.WebhookAuthenticationForTesting;
import org.example.security.webhook.WebhookConfigurer;
import org.example.security.webhook.WebhookFilter;
import org.example.transactions.security.SetPasswordTransaction;
import org.security.application.CreateUserManager;
import org.security.application.RefreshUserAuthentication;
import org.security.application.SetTeammatePassword;
import org.security.application.UsernamePasswordAuthenticationProvider;
import org.security.ports.spi.JwtEncoder;
import org.security.ports.spi.PasswordSecurity;
import org.security.ports.spi.inMemory.FakePasswordEncoder;
import org.shared.spi.InMemoryUserRepository;
import org.shared.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@Profile("test")
public class SecurityTestConfig {
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    WebhookFilter webhookFilter;
    @Autowired
    WebhookAuthenticationForTesting webhookAuthenticationProvider;
    @Autowired
    JwtAuthenticationForTesting jwtAuthenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize -> authorize.requestMatchers( "/login", "/create-account").permitAll()
                .anyRequest().authenticated());
        http.apply(new WebhookConfigurer()
                .withJwtFilter(webhookFilter)
                .withAuthenticationProvider(webhookAuthenticationProvider));
        http.apply(new JwtConfigurer()
                .withJwtFilter(jwtFilter)
                .withAuthenticationProvider(jwtAuthenticationProvider));
        return http.build();
    }

    @Bean
    UserRepository userRepository(){
        return new InMemoryUserRepository();
    }
    @Bean
    JwtEncoder jwtService(){
        return new FakeTokenService();
    }

    @Bean
    PasswordSecurity passwordSecurity(){
        return new FakePasswordEncoder();
    }

    @Bean
    UsernamePasswordAuthenticationProvider usernamePasswordAuthentication(){
        return new UsernamePasswordAuthenticationProvider(userRepository(), passwordSecurity(), jwtService());
    }
    @Bean
    CreateUserManager signup(){
        return new CreateUserManager(userRepository(), jwtService());
    }



    @Bean
    SetPasswordTransaction changePassword(){
        return new SetPasswordTransaction(new SetTeammatePassword(userRepository(), passwordSecurity()));
    }

    @Bean
    RefreshUserAuthentication refreshAuthentication(){
        return new RefreshUserAuthentication(userRepository(), jwtService());
    }
}
