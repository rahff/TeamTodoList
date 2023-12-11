package org.example.config.account;


import com.stripe.Stripe;
import org.example.persistance.repositories.security.query.InMemoryAccountUserDataAccess;
import org.query.account.api.AccountDetailsViewQuery;
import org.query.account.spi.UserDataAccess;
import org.security.ports.spi.PaymentGateway;
import org.security.ports.spi.inMemory.FakePaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountQueryTestConfig {


    @Bean
    UserDataAccess userDataAccess(){
        return new InMemoryAccountUserDataAccess();
    }


    @Bean
    AccountDetailsViewQuery accountDetailsViewQuery() {
        return new AccountDetailsViewQuery(userDataAccess());
    }

}
