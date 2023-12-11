package org.example.config.security;

import org.example.security.FakeTokenService;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("test")
public class SecurityTestConfig {

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
