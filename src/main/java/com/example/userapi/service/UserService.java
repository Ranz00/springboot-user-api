package com.example.userapi.service;

import com.example.userapi.dto.UserDto;
import com.example.userapi.entity.User;
import com.example.userapi.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
  private final UserRepository repo;
  public UserService(UserRepository repo) { this.repo = repo; }

  public UserDto create(String username, String email, String rawPassword, String role) {
    User u = new User();
    u.setUsername(username);
    u.setEmail(email);
    u.setPasswordHash(BCrypt.hashpw(rawPassword, BCrypt.gensalt()));
    u.setRole(role == null ? "USER" : role);
    return toDto(repo.save(u));
  }

  public List<UserDto> list() { return repo.findAll().stream().map(this::toDto).toList(); }
  public UserDto get(Long id) { return repo.findById(id).map(this::toDto).orElseThrow(); }
  public void delete(Long id) { repo.deleteById(id); }

  public User findByLogin(String usernameOrEmail) {
    return repo.findByUsername(usernameOrEmail)
        .or(() -> repo.findByEmail(usernameOrEmail))
        .orElseThrow();
  }

  private UserDto toDto(User u) {
    return new UserDto(u.getId(), u.getUsername(), u.getEmail(), u.getRole(), u.isActive());
  }
}
