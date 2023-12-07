package org.security.ports.spi;

import org.security.ports.dto.CheckoutSession;
import org.security.ports.dto.PriceDto;

import java.util.List;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(String priceId) throws Exception;
    List<PriceDto> getSubscriptionPrices() throws Exception;
}
