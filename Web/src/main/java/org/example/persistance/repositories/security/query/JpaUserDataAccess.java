package org.example.persistance.repositories.security.query;


import org.example.persistance.entities.security.AppUser;
import org.example.persistance.repositories.security.springData.AppUserRepository;
import org.query.account.dto.UserDto;
import org.query.shared.spi.UserDataAccess;
import org.query.team.dto.TeammateDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Profile("prod")
public class JpaUserDataAccess implements UserDataAccess {
    private final AppUserRepository userRepository;

    public JpaUserDataAccess(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<TeammateDto> getTeammateByUserId(String userId) {
        return userRepository.findById(userId).map(TeammateMapper::mapTo);
    }


    public List<TeammateDto> getAllTeammate(String accountId) {
        return userRepository.findByAccountId(accountId)
                .stream().filter(this::onlyTeammates)
                .map(TeammateMapper::mapTo)
                .toList();
    }

    private boolean onlyTeammates(AppUser appUser) {
        return appUser.getAuthority().getUserRole().equals("TEAMMATE");
    }

    @Override
    public Optional<UserDto> getUserById(String userId) {
        return Optional.empty();
    }
}

class TeammateMapper {

    public static TeammateDto mapTo(AppUser appUser){
        return new TeammateDto(appUser.getId(), appUser.getName(), appUser.getEmail());
    }
}