package org.example.config.account;


import org.example.transactions.security.CreateManagerAccountTransaction;
import org.security.application.CreateManagerAccount;
import org.security.application.CreateUserManager;
import org.security.ports.spi.AccountRepository;
import org.security.ports.spi.PaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("prod")
public class AccountModuleConfig {

    @Autowired
    PaymentGateway paymentGateway;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CreateUserManager signup;
    @Bean
    CreateManagerAccountTransaction createAccount(){
        return new CreateManagerAccountTransaction(new CreateManagerAccount(accountRepository, paymentGateway), signup);
    }
}
