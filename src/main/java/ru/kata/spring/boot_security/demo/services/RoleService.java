package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByRole(String role);

    List<Role> findAll();

    void save(Role role);
}
