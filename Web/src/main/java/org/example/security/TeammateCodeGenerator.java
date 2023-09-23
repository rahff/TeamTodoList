package org.example.security;

import org.security.ports.dto.GeneratedCodePair;
import org.security.ports.spi.CodeGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class TeammateCodeGenerator implements CodeGenerator {
  private final PasswordEncoder passwordEncoder;

  public TeammateCodeGenerator(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public GeneratedCodePair generateCode() {
    var code = RandomCodeGenerator.generate();
    return new GeneratedCodePair(passwordEncoder.encode(code), code);
  }
}
