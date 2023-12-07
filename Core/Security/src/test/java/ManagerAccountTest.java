import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.security.application.CreateManagerAccount;
import org.security.ports.dto.CheckoutSession;
import org.security.ports.spi.AccountRepository;
import org.security.ports.spi.PaymentGateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ManagerAccountTest {

  private AccountRepository repository;
  private CreateManagerAccount command;
  private PaymentGateway paymentGateway;

  @BeforeEach
  void setup(){
    repository = Mockito.mock(AccountRepository.class);
    paymentGateway = Mockito.mock(PaymentGateway.class);
    command = new CreateManagerAccount(repository, paymentGateway);
  }
  @Test
  void AManagerCreateAnAccount() throws Exception {
    when(paymentGateway.createCheckoutSession("priceID")).thenReturn(new CheckoutSession("checkoutId", "http://fakeUrl.com"));
    var checkoutSession = command.execute("accountId", "priceID");
    verify(repository).save("accountId");
    assertEquals(checkoutSession.checkoutUrl(), "http://fakeUrl.com");
  }
}
