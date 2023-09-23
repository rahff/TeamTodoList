import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.security.application.CreateManagerAccount;
import org.security.ports.spi.AccountRepository;

import static org.mockito.Mockito.verify;

public class ManagerAccountTest {

  private AccountRepository repository;
  private CreateManagerAccount command;

  @BeforeEach
  void setup(){
    repository = Mockito.mock(AccountRepository.class);
    command = new CreateManagerAccount(repository);
  }
  @Test
  void AManagerCreateAnAccount(){
    command.execute("accountId");
    verify(repository).save("accountId");
  }
}
