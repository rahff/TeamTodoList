package org.team.application.commands;


import org.shared.dto.UserDto;
import org.shared.spi.UserRepository;
import org.team.ports.api.AddTeammate;
import org.team.ports.dto.CreateTeammateRequest;
import org.team.ports.dto.TeammateJoiningRequest;
import org.team.ports.spi.CodeGenerator;

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
