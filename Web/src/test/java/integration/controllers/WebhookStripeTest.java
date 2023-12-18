package integration.controllers;

import org.example.MainTest;
import org.example.security.webhook.StripeEvent;
import org.example.security.webhook.WebhookAuthenticationToken;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shared.dto.SubscriptionDto;
import org.shared.dto.UserDto;
import org.shared.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import utils.StringProvider;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = MainTest.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WebhookStripeTest extends BaseControllerTest {

    @Autowired
    UserRepository userRepository;
    private WebhookDataFixture dataFixture;

    @BeforeEach
    void setup(){
        dataFixture = new WebhookDataFixture();
    }
    @Test
    void checkoutSessionCompleted() throws Exception {
        userRepository.save(dataFixture.getUserDto());
        initSecurityContext(new WebhookAuthenticationToken(null, dataFixture.getFakeStripeEvent()));
        mockMvc.perform(post("/webhook/payment").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
        var manager = userRepository.findByEmail("sachapokemon@gamil.com").orElse(null);
        assertNotNull(manager);
        assertEquals("MANAGER", manager.role());
        assertNotNull(manager.subscription().orElse(null));
        assertTrue(manager.subscription().get().paid());
    }
}

class WebhookDataFixture {

    UserDto getUserDto(){
        return new UserDto(
                StringProvider.unique(),
                "sachapokemon@gamil.com",
                "sacha",
                "12345",
                "MANAGER",
                "accountID",
                Optional.of(
                        new SubscriptionDto(
                                "*subscriptionId*",
                                false)));
    }
    StripeEvent getFakeStripeEvent(){
        return new StripeEvent(new JSONObject("{\"id\":\"*subscriptionId*\"}"), "checkout.session.completed");
    }
}
