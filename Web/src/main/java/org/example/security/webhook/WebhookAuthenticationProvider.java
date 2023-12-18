package org.example.security.webhook;

import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.net.Webhook;
import org.example.payment.WebhookConfig;
import org.json.JSONObject;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;


@Service
@Profile("prod")
public class WebhookAuthenticationProvider implements AuthenticationProvider {

    private final WebhookConfig webhookConfig;

    public WebhookAuthenticationProvider(WebhookConfig webhookConfig) {
        this.webhookConfig = webhookConfig;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try{
            var signature = authentication.getCredentials().toString();
            var body = authentication.getDetails().toString();
            var event = Webhook.constructEvent(body, signature, webhookConfig.getWebhookSecret());
            EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
            JSONObject dataJson = new JSONObject(dataObjectDeserializer.deserializeUnsafe());
            return new WebhookAuthenticationToken(null, new StripeEvent(dataJson, event.getType()));
        }catch (Exception e){
            throw new BadCredentialsException("invalid signature");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(WebhookAuthenticationToken.class);
    }
}
