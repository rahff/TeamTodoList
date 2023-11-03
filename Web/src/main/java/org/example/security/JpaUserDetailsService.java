package org.example.security;

import org.example.persistance.entities.security.AppUser;
import org.example.persistance.entities.security.Authority;
import org.example.persistance.repositories.security.command.AppUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class JpaUserDetailsService implements UserDetailsService {

  private final AppUserRepository userRepository;

  public JpaUserDetailsService(AppUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var user = userRepository.findByEmail(email)
      .orElseThrow(() -> new UsernameNotFoundException("bad credentials"));
    return mapToUserDetails(user);
  }

  private UserDetails mapToUserDetails(AppUser user){
    return User.withUsername(user.getEmail())
      .password(user.getPassword())
      .authorities(getUserAuthority(user.getAuthority()))
      .build();
  }

  private Collection<GrantedAuthority> getUserAuthority(Authority authority) {
    return List.of(new SimpleGrantedAuthority(authority.getUserRole()));
  }
}
