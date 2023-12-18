import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.security.application.UpdateManagerSubscription;
import org.security.ports.dto.PaymentSuccessEvent;
import org.shared.dto.SubscriptionDto;
import org.shared.dto.UserDto;
import org.shared.spi.InMemoryUserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateManagerSubscriptionTest {

    private UpdateManagerSubscription command;
    private InMemoryUserRepository userRepository;
    private UpdateSubscriptionDataFixture dataFixture;
    @BeforeEach
    void setup(){
        dataFixture = new UpdateSubscriptionDataFixture();
        userRepository = new InMemoryUserRepository(dataFixture.initialUserRepository());
        command = new UpdateManagerSubscription(userRepository);
    }
    @Test
    void stripeWebhookOnPaymentSucceed(){
        command.execute(new PaymentSuccessEvent("subscriptionId"));
        assertTrue(userRepository.items().contains(
                new UserDto(
                "teammateId",
                "rahff@gmail.com",
                "rahff",
                "12345",
                "MANAGER",
                "accountId",
                Optional.of(
                        new SubscriptionDto(
                                "subscriptionId",
                                true
                        )
                )
                        )
                )
        );
    }
}

class UpdateSubscriptionDataFixture {
    public List<UserDto> initialUserRepository() {
        return List.of(
                new UserDto(
                        "teammateId",
                        "rahff@gmail.com",
                        "rahff",
                        "12345",
                        "MANAGER",
                        "accountId",
                        Optional.of(new SubscriptionDto("subscriptionId", false)))
        );
    }
}