package org.security.application;

import org.security.ports.dto.CreateTeammateRequest;
import org.security.ports.dto.TeammateJoiningRequest;
import org.security.ports.dto.UserDto;
import org.security.ports.spi.AddTeammate;
import org.security.ports.spi.CodeGenerator;
import org.security.ports.spi.UserRepository;

public class CreateTeammate implements AddTeammate {

  private final UserRepository userRepository;
  private final CodeGenerator codeGenerator;
  private final String ROLE = "TEAMMATE";

  public CreateTeammate(UserRepository userRepository, CodeGenerator codeGenerator) {
    this.userRepository = userRepository;
    this.codeGenerator = codeGenerator;
  }

  public TeammateJoiningRequest execute(CreateTeammateRequest request) {
    var generatedCode = codeGenerator.generateCode();
    var teammate = userRepository.save(new UserDto(request.teammateId(), request.email(), request.name(), generatedCode.encoded(), ROLE, request.accountId()));
    return new TeammateJoiningRequest(teammate.email(), teammate.name(), generatedCode.rawCode()) ;
  }
}
