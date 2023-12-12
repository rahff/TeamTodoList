package org.example.config.account;


import org.example.config.security.SecurityConfig;
import org.example.transactions.security.CreateManagerAccountTransaction;
import org.security.application.CreateManagerAccount;
import org.security.application.CreateUserManager;
import org.security.ports.spi.AccountRepository;
import org.security.ports.spi.JwtEncoder;
import org.security.ports.spi.PaymentGateway;
import org.shared.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("prod")
@Import(SecurityConfig.class)
public class AccountModuleConfig {

    @Autowired
    PaymentGateway paymentGateway;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtEncoder jwtEncoder;

    @Bean
    CreateUserManager signup() {
        return new CreateUserManager(userRepository, jwtEncoder);
    };
    @Bean
    CreateManagerAccount createManagerAccount(){
        return new CreateManagerAccount(accountRepository, paymentGateway);
    }
    @Bean
    CreateManagerAccountTransaction createAccount(){
        return new CreateManagerAccountTransaction(createManagerAccount(), signup());
    }
}
