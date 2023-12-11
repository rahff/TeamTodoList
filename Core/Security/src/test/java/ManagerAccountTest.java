import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.security.application.CreateManagerAccount;
import org.security.ports.dto.CheckoutSession;
import org.security.ports.spi.AccountRepository;
import org.security.ports.spi.PaymentGateway;
import org.security.ports.spi.inMemory.FakePaymentGateway;
import org.security.ports.spi.inMemory.InMemoryAccountRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ManagerAccountTest {

  private InMemoryAccountRepository repository;
  private CreateManagerAccount command;

    @BeforeEach
  void setup(){
    repository = new InMemoryAccountRepository();
    FakePaymentGateway paymentGateway = new FakePaymentGateway();
    command = new CreateManagerAccount(repository, paymentGateway);
  }
  @Test
  void AManagerCreateAnAccount() throws Exception {
    var checkoutSession = command.execute("accountId", "priceID");
    assertTrue(repository.items().contains("accountId"));
    assertEquals(checkoutSession.checkoutUrl(), "http://fakeUrl/priceID");
  }
}
