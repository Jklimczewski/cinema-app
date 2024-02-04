package com.example.backend.config;

import java.util.stream.Collectors;

import com.example.backend.user.UserEntity;
import com.example.backend.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity user = userRepository.findByEmail(email);
    if (user != null) {
      User authUser = new User(
          user.getEmail(),
          user.getPassword(),
          user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
              .collect(Collectors.toList()));
      return authUser;
    } else {
      throw new UsernameNotFoundException("Invalid email or password");
    }
  }
}