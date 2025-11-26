package com.example.userapi.controller;

import com.example.userapi.dto.UserDto;
import com.example.userapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<UserDto> list() { return userService.list(); }

  @GetMapping("/{id}")
  public UserDto get(@PathVariable Long id) { return userService.get(id); }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
