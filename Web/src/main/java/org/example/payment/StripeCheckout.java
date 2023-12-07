package org.example.payment;

import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.PriceCollection;
import com.stripe.model.checkout.Session;
import com.stripe.param.PriceListParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.security.ports.dto.CheckoutSession;
import org.security.ports.dto.PriceDto;
import org.security.ports.spi.PaymentGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Profile("prod")
public class StripeCheckout implements PaymentGateway {

    @Value("${hostname.appDomain}")
    private String appDomain;
    @Value("${hostname.orgDomain}")
    private String orgDomain;
    public CheckoutSession createCheckoutSession(String priceId) throws StripeException {
                SessionCreateParams params = new SessionCreateParams.Builder()
                .setSuccessUrl(appDomain)
                .setCancelUrl(orgDomain)
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                .addLineItem(new SessionCreateParams.LineItem.Builder()
                        .setQuantity(1L)
                        .setPrice(priceId)
                        .build()
                )
                .build();
        Session session = Session.create(params);
        return new CheckoutSession(session.getId(), session.getUrl());
    }

    public List<PriceDto> getSubscriptionPrices() throws StripeException {
        var params = PriceListParams.builder().build();
        PriceCollection priceCollection = Price.list(params);
        return priceCollection.getData().stream().map(price -> new PriceDto(price.getId())).toList();
    }
}
