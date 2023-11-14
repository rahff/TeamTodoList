package org.example.config.account;


import org.query.account.api.AccountDetailsViewQuery;
import org.query.account.spi.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountQueryBeanConfig {

  @Autowired
  UserDataAccess userDataAccess;
  @Bean
  AccountDetailsViewQuery accountDetailsViewQuery() {
    return new AccountDetailsViewQuery(userDataAccess);
  }
}
