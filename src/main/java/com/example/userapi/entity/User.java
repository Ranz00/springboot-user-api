package com.example.userapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"}),
    @UniqueConstraint(columnNames = {"username"})
})
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank @Size(min = 3, max = 50)
  private String username;

  @NotBlank @Email
  private String email;

  @NotBlank
  private String passwordHash;

  private String role = "USER";
  private boolean active = true;
  private Instant createdAt = Instant.now();

  // getters and setters...
}
