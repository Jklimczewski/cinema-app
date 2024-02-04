package com.example.backend.user;

import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
  }

  @Override
  public UserEntity save(RegistrationWriteDto writeDto) {
    Role role = roleRepository.findByName("USER");
    UserEntity userToCreate = UserEntity.builder()
        .username(writeDto.getUsername())
        .email(writeDto.getEmail())
        .password(passwordEncoder.encode(writeDto.getPassword()))
        .roles(Arrays.asList(role))
        .build();

    return userRepository.save(userToCreate);
  }

  @Override
  public UserEntity findByEmail(String email) {
    return userRepository.findByEmail(email);
  }
}