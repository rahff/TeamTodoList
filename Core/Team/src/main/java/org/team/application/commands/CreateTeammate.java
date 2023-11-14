package org.team.application.commands;


import org.shared.dto.UserDto;
import org.shared.spi.UserRepository;
import org.team.ports.api.AddTeammate;
import org.team.ports.dto.CreateTeammateRequest;
import org.team.ports.dto.TeammateJoiningRequest;
import org.team.ports.spi.CodeGenerator;
import org.team.ports.spi.TeammateRepository;

public class CreateTeammate implements AddTeammate {

  private final UserRepository userRepository;
  private final CodeGenerator codeGenerator;
  private final TeammateRepository teammateRepository;

  public CreateTeammate(UserRepository userRepository, CodeGenerator codeGenerator, TeammateRepository teammateRepository) {
    this.userRepository = userRepository;
    this.codeGenerator = codeGenerator;
    this.teammateRepository = teammateRepository;
  }

  public TeammateJoiningRequest execute(CreateTeammateRequest request) {
    String ROLE = "TEAMMATE";
    var generatedCode = codeGenerator.generateCode();
    var userDto = userRepository.save(new UserDto(request.teammateId(), request.email(), request.name(), generatedCode.encoded(), ROLE, request.accountId()));
    teammateRepository.saveUserAsTeammate(userDto);
    return new TeammateJoiningRequest(userDto.email(), userDto.name(), generatedCode.rawCode()) ;
  }
}
