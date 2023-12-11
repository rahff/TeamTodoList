package org.security.ports.spi.inMemory;

import org.security.ports.dto.CheckoutSession;
import org.security.ports.dto.PriceDto;
import org.security.ports.spi.PaymentGateway;

import java.util.List;

public class FakePaymentGateway implements PaymentGateway {

    public CheckoutSession createCheckoutSession(String priceId) {
        return new CheckoutSession("checkoutSessionId", "http://fakeUrl/"+priceId);
    }

    public List<PriceDto> getSubscriptionPrices() {
        return List.of(new PriceDto("priceId"));
    }

}
