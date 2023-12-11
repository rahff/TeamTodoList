package org.team.application.commands;


import org.shared.dto.UserDto;
import org.shared.spi.UserRepository;
import org.team.ports.dto.CreateTeammateRequest;
import org.team.ports.dto.TeammateJoiningRequest;
import org.team.ports.spi.CodeGenerator;
import org.team.ports.spi.TeammateRepository;

import java.util.Optional;

public class CreateTeammate {

  private final UserRepository userRepository;
  private final CodeGenerator codeGenerator;
  private final TeammateRepository teammateRepository;

  public CreateTeammate(UserRepository userRepository,
                        CodeGenerator codeGenerator,
                        TeammateRepository teammateRepository) {
    this.userRepository = userRepository;
    this.codeGenerator = codeGenerator;
    this.teammateRepository = teammateRepository;
  }

  public TeammateJoiningRequest execute(CreateTeammateRequest request) {
    var generatedCode = codeGenerator.generateCode();
    var userDto = userRepository.save(fromRequest(request, generatedCode.encoded()));
    teammateRepository.saveUserAsTeammate(userDto);
    return new TeammateJoiningRequest(userDto.email(), userDto.name(), generatedCode.rawCode()) ;
  }

  private UserDto fromRequest(CreateTeammateRequest request, String generatedCode) {
    String ROLE = "TEAMMATE";
    return new UserDto(
            request.teammateId(),
            request.email(),
            request.name(),
            generatedCode,
            ROLE,
            request.accountId(),
            Optional.empty());
  }
}
