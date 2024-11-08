package com.example.httpserver.entity;

import com.example.httpserver.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "username", "role"})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    @NotNull
    private Role role;

    @Column(name = "password", nullable = false)
    @NotNull
    private String password;
}
