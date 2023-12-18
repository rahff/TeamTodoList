package adapters.command;


import org.example.Main;
import org.example.payment.StripeCheckout;
import org.example.payment.StripeConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.security.ports.spi.PaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(classes = Main.class)
@Profile("prod")
public class StripeCheckoutSessionTest {

    private PaymentGateway paymentGateway;

    @Autowired
    StripeConfig config;
    @BeforeEach
    void setup(){
        paymentGateway = new StripeCheckout(config);
    }

    @Test
    void createCheckoutSession() throws Exception {
        assertDoesNotThrow(()-> paymentGateway.createCheckoutSession("price_1OOCmjGLEzO6wwJZbeC8rx0j"));
    }
}
