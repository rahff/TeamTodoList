package integration.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.security.jwt.JwtAuthenticationToken;
import org.junit.jupiter.api.BeforeEach;
import org.shared.dto.SubscriptionDto;
import org.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;


public class BaseControllerTest {
  @Autowired
  protected MockMvc mockMvc;
  protected final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  protected WebApplicationContext context;

  protected final Authentication fakeManagerAuthentication = new JwtAuthenticationToken(new UserDto("userId", "rahff@gmail.com", "Rahff", null, "MANAGER", "accountId", Optional.of(new SubscriptionDto("id", true))), "123Token", List.of(new SimpleGrantedAuthority("MANAGER")));
  protected final Authentication fakeTeammateAuthentication = new JwtAuthenticationToken(new UserDto("teammateId", "teammate@gmail.com", "Mikki", null, "TEAMMATE", "accountId", Optional.empty()), "123Token", List.of(new SimpleGrantedAuthority("TEAMMATE")));

  @BeforeEach
  void setUp(){
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    initSecurityContext(fakeManagerAuthentication);
  }
  protected void initSecurityContext(Authentication authentication) {
    SecurityContextHolder.clearContext();
    var newContext = SecurityContextHolder.createEmptyContext();
    SecurityContextHolder.setContext(newContext);
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
