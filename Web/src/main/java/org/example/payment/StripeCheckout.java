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

    private StripeConfig config;
   public StripeCheckout(StripeConfig config){
       this.config = config;
   }
    public CheckoutSession createCheckoutSession(String priceId) throws StripeException {
                SessionCreateParams params = new SessionCreateParams.Builder()
                .setSuccessUrl(config.getSuccessUrl())
                .setCancelUrl(config.getCancelUrl())
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                .addLineItem(new SessionCreateParams.LineItem.Builder()
                        .setQuantity(1L)
                        .setPrice(priceId)
                        .build()
                )
                .build();
        Session session = Session.create(params);
        System.out.println("stripe session: "+session.toJson());
        return new CheckoutSession(session.getId(), session.getUrl());
    }

    public List<PriceDto> getSubscriptionPrices() throws StripeException {
        var params = PriceListParams.builder().build();
        PriceCollection priceCollection = Price.list(params);
        return priceCollection.getData().stream().map(price -> new PriceDto(price.getId())).toList();
    }
}
