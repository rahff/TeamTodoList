package org.example.payment;

import org.security.ports.dto.CheckoutSession;
import org.security.ports.dto.PriceDto;
import org.security.ports.spi.PaymentGateway;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("test")
public class FakeCheckout implements PaymentGateway {
    @Override
    public CheckoutSession createCheckoutSession(String priceId) {
        return new CheckoutSession( "sessionId", "http://fakeUrl.com");
    }

    @Override
    public List<PriceDto> getSubscriptionPrices() {
        return List.of();
    }
}
