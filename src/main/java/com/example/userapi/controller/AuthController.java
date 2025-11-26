package com.example.userapi.controller;

import com.example.userapi.dto.LoginRequest;
import com.example.userapi.dto.SignupRequest;
import com.example.userapi.entity.User;
import com.example.userapi.security.JwtUtil;
import com.example.userapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final UserService userService;
  private final JwtUtil jwtUtil;

  public AuthController(UserService userService, JwtUtil jwtUtil) {
    this.userService = userService;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody @Validated SignupRequest req) {
    var dto = userService.create(req.username(), req.email(), req.password(), "USER");
    return ResponseEntity.ok(dto);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Validated LoginRequest req) {
    User u = userService.findByLogin(req.usernameOrEmail());
    if (!BCrypt.checkpw(req.password(), u.getPasswordHash())) {
      return ResponseEntity.status(401).body("Invalid credentials");
    }
    String token = jwtUtil.generateToken(u.getUsername(), u.getRole());
    return ResponseEntity.ok(token);
  }
}
