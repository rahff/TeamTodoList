package org.example.config.account;


import org.example.config.security.SecurityConfig;
import org.example.payment.StripeCheckout;
import org.example.payment.StripeConfig;
import org.example.payment.WebhookConfig;
import org.example.transactions.security.CreateManagerAccountTransaction;
import org.example.transactions.security.UpdateSubscriptionTransaction;
import org.security.application.CreateManagerAccount;
import org.security.application.CreateUserManager;
import org.security.application.UpdateManagerSubscription;
import org.security.ports.spi.AccountRepository;
import org.security.ports.spi.JwtEncoder;
import org.security.ports.spi.PaymentGateway;
import org.shared.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("prod")
@Import(SecurityConfig.class)
public class AccountModuleConfig {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtEncoder jwtEncoder;
    @Value("${hostname.appDomain}")
    String stripe_success_url;
    @Value("${hostname.orgDomain}")
    String stripe_cancel_url;
    @Value("${stripe.webhook.key}")
    String stripe_webhook_key;

    @Bean
    StripeConfig stripeConfig(){
        return new StripeConfig(stripe_success_url, stripe_cancel_url);
    }
    @Bean
    WebhookConfig webhookConfig(){
        return new WebhookConfig(stripe_webhook_key);
    }
    @Bean
    PaymentGateway paymentGateway(){
        return new StripeCheckout(stripeConfig());
    }

    @Bean
    CreateUserManager signup() {
        return new CreateUserManager(userRepository, jwtEncoder);
    };
    @Bean
    CreateManagerAccount createManagerAccount(){
        return new CreateManagerAccount(accountRepository, paymentGateway());
    }
    @Bean
    CreateManagerAccountTransaction createAccount(){
        return new CreateManagerAccountTransaction(createManagerAccount(), signup());
    }

    @Bean
    UpdateSubscriptionTransaction updateSubscriptionTransaction(){
        return new UpdateSubscriptionTransaction(new UpdateManagerSubscription(userRepository));
    }
}
