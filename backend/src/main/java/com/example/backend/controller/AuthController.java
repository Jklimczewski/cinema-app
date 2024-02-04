package com.example.backend.controller;

import com.example.backend.user.RegistrationWriteDto;
import com.example.backend.user.UserEntity;
import com.example.backend.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/login")
  public String loginPage() {
    return "login";
  }

  @GetMapping("/register")
  public String registerPage(Model model) {
    RegistrationWriteDto user = new RegistrationWriteDto();
    model.addAttribute("user", user);
    return "register";
  }

  @PostMapping("/register/save")
  public String register(@ModelAttribute("user") RegistrationWriteDto writeDto, Model model) {
    UserEntity userExists = userService.findByEmail(writeDto.getEmail());
    if (userExists != null) {
      return "redirect:/register?fail";
    }
    userService.save(writeDto);
    return "redirect:/login";
  }
}