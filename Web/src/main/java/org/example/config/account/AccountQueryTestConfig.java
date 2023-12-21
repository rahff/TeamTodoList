package org.example.config.account;



import org.query.account.api.AccountDetailsViewQuery;

import org.query.shared.spi.UserDataAccess;
import org.query.team.spi.fakes.InMemoryUserDataAccess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class AccountQueryTestConfig {


    @Bean
    UserDataAccess userDataAccess(){
        return new InMemoryUserDataAccess();
    }


    @Bean
    AccountDetailsViewQuery accountDetailsViewQuery() {
        return new AccountDetailsViewQuery(userDataAccess());
    }

}
