package org.example.config.account;


import org.example.config.security.SecurityTestConfig;
import org.example.transactions.security.CreateManagerAccountTransaction;
import org.example.transactions.security.UpdateSubscriptionTransaction;
import org.security.application.CreateManagerAccount;
import org.security.application.CreateUserManager;
import org.security.application.UpdateManagerSubscription;
import org.security.ports.spi.AccountRepository;
import org.security.ports.spi.PaymentGateway;
import org.security.ports.spi.inMemory.FakePaymentGateway;
import org.security.ports.spi.inMemory.InMemoryAccountRepository;
import org.shared.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
@Import(SecurityTestConfig.class)
public class AccountTestConfig {

    @Autowired
    CreateUserManager signup;
    @Autowired
    UserRepository userRepository;

    @Bean
    AccountRepository accountRepository() {
        return new InMemoryAccountRepository();
    }

    @Bean
    PaymentGateway paymentGateway(){
        return new FakePaymentGateway();
    }

    @Bean
    CreateManagerAccountTransaction createAccount(){
        return new CreateManagerAccountTransaction(new CreateManagerAccount(accountRepository(), paymentGateway()), signup);
    }

    @Bean
    UpdateSubscriptionTransaction updateSubscriptionTransaction() {
        return new UpdateSubscriptionTransaction(new UpdateManagerSubscription(userRepository));
    }
}
