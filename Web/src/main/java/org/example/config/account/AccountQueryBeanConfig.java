package org.example.config.account;


import com.stripe.Stripe;
import org.query.account.api.AccountDetailsViewQuery;
import org.query.account.spi.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"prod"})
public class AccountQueryBeanConfig {

  @Value("${stripe.api_key}")
  private String stripeApiKey;
  @Autowired
  UserDataAccess userDataAccess;


  @Bean
  AccountDetailsViewQuery accountDetailsViewQuery() {
    return new AccountDetailsViewQuery(userDataAccess);
  }

  @Bean
  void setStripeApiKey() {
    Stripe.apiKey = stripeApiKey;
  }


}
